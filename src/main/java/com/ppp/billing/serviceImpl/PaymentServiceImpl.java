package com.ppp.billing.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.Facture;
import com.ppp.billing.model.Payment;
import com.ppp.billing.repository.FactureRepository;
import com.ppp.billing.repository.PaymentRepository;
import com.ppp.billing.service.PaymentService;
import com.ppp.billing.service.StaffService;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final FactureRepository factureRepo;
    
    @Autowired
    private StaffService staffService;


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

    	    Payment p = new Payment();
    	    p.setFacture(f);
    	    p.setAmountPaid(payment.getAmountPaid());
    	    p.setMethod(payment.getMethod());
    	    p.setReference(payment.getReference());
    	    p.setReceivedBy(userName);
    	    p = paymentRepository.save(p);

    	    // update invoice balance & flag
    	    f.setAmountPaid(f.getTotalPaid().doubleValue());
    	    f.setBalance(f.getBalance().doubleValue());
    	    f.setFullyPaid(f.isFullyPaid());
    	    factureRepo.save(f);

    	    return p;
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
}
