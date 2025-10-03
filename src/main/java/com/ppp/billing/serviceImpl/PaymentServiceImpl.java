package com.ppp.billing.serviceImpl;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.itextpdf.io.IOException;
import com.ppp.billing.Dto.PaymentDTO;
import com.ppp.billing.model.Facture;
import com.ppp.billing.model.Payment;
import com.ppp.billing.model.PaymentMethod;
import com.ppp.billing.model.RefrenceNumberGenerator;
import com.ppp.billing.repository.FactureRepository;
import com.ppp.billing.repository.PaymentRepository;
import com.ppp.billing.service.PaymentService;
import com.ppp.billing.service.PdfService;
import com.ppp.billing.service.StaffService;
import com.ppp.user.model.User;
import com.ppp.user.repository.UserRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final FactureRepository factureRepo;
    
    
    @Autowired
    private StaffService staffService;
    
    @Autowired
    private PdfService pdfService;
    
    @Autowired
    private UserRepository staffRepository;


    public PaymentServiceImpl(PaymentRepository paymentRepository,FactureRepository factureRepo) {
        this.paymentRepository = paymentRepository;
        this.factureRepo = factureRepo;
    }

    @Override
	@Transactional
    public Payment savePayment(Long factureId,Payment payment) {
    	    Facture f = factureRepo.findById(factureId)
    	                           .orElseThrow(() -> new RuntimeException("Invoice not found"));
      		String userName = SecurityContextHolder.getContext().getAuthentication().getName();

      		User staff = staffRepository.findByUsername(userName);
      	
    	    Payment p = new Payment();
    	    p.setFacture(f);
    	    p.setAmountPaid(payment.getAmountPaid());
    	    p.setMethod(payment.getMethod());
    	    p.setReference(RefrenceNumberGenerator.nextPaymentRef());
    		p.setReceivedBy(staff);              
    		p.setMethod(PaymentMethod.valueOf(payment.getPaymentMethod().toUpperCase()));
    	    p = paymentRepository.save(p);

    	    BigDecimal newPaid = f.getTotalPaid().add(payment.getAmountPaid());
    	    
            BigDecimal newBal  = f.getNetAmount().subtract(newPaid);
            f.setAmountPaid(f.getNetAmount().subtract(newBal));

        
            f.setBalance(newBal.doubleValue());                // keep your double field
            f.setFullyPaid(newBal.compareTo(BigDecimal.ZERO) <= 0);
            
            if(f.isFullyPaid()) {
            	f.setStatus("PAID");
            } else {
            	f.setStatus("PENDING");
            }
           //.setStatus(newBal.compareTo(BigDecimal.ZERO) <= 0 ? "PAID" : "PENDING");

    	    factureRepo.save(f);

    	    return p;
    	}
    	
    @Scheduled(cron = "0 0 0 * * *")   // midnight
    public void resetSerials() {
    	RefrenceNumberGenerator.resetAll(); // static method that sets PAT_SERIAL.set(1) etc.
    }
    	
        

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Payment> getPaymentsByFacture(Long factureId) {
        return paymentRepository.findByFactureId(factureId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Payment savePaymentFacture(PaymentDTO dto) throws java.io.IOException {
        /* 1. load invoice & cashier */
        Facture f = factureRepo.findById(dto.getFactureId())
                               .orElseThrow(() -> new RuntimeException("Invoice not found"));
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User staff = staffRepository.findByUsername(userName);

        /* 2. create payment */
        Payment p = new Payment();
        p.setFacture(f);
        p.setAmountPaid(dto.getAmountPaid());
        p.setMethod(PaymentMethod.valueOf(dto.getPaymentMethod().toUpperCase()));
        p.setReference(RefrenceNumberGenerator.nextPaymentRef());
        p.setReceivedBy(staff);
        p = paymentRepository.save(p);
        /* 4.  generate & store receipt  (add this block) */
        try {
            File receipt = pdfService.generatePaymentReceipt(p.getId()); // your existing method
            p.setReceiptPath(receipt.getAbsolutePath());      // new column in Payment entity
            paymentRepository.save(p);                        // store path
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        /* 3. update invoice balance & status */
        BigDecimal newPaid = f.getTotalPaid();         
        BigDecimal newBal  = f.getNetAmount().subtract(newPaid);
        f.setAmountPaid(f.getTotalPaid());
        f.setBalance(newBal.doubleValue());
        f.setFullyPaid(newBal.compareTo(BigDecimal.ZERO) <= 0);
        f.setStatus(f.isFullyPaid() ? "PAID" : "PENDING");

        factureRepo.save(f);
        return p;
    }

	@Override
	public Payment findById(Long paymentId) {
		return paymentRepository.findById(paymentId).get();
	}
	
}
