package com.ppp.billing.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.Dto.PaymentItemDto;
import com.ppp.billing.model.Patient;
import com.ppp.billing.model.PaymentItem;
import com.ppp.billing.model.ServiceItem;
import com.ppp.billing.repository.PatientRepository;
import com.ppp.billing.repository.PaymentItemRepository;
import com.ppp.billing.repository.ServiceItemRepository;

@Service
public class PaymentItemService {
	
	
	@Autowired
	private  PaymentItemRepository paymentItemRepository;
	
	private final PaymentItemRepository paymentRepository;
    private final PatientRepository patientRepository;
    private final ServiceItemRepository serviceItemRepository;

    public PaymentItemService(PaymentItemRepository paymentRepository,
                          PatientRepository patientRepository,
                          ServiceItemRepository serviceItemRepository) {
        this.paymentRepository = paymentRepository;
        this.patientRepository = patientRepository;
        this.serviceItemRepository = serviceItemRepository;
    }

    public PaymentItem savePayment(PaymentItem payment) {
        return paymentItemRepository.save(payment);
    }

    public List<PaymentItem> getAllPayments() {
    	
    	List<PaymentItem> payment  =paymentItemRepository.findAll();
    	payment.sort(Comparator.comparing(PaymentItem::getId));
    	Collections.reverse(payment);
        return payment;
    }

    public PaymentItem getPaymentById(Long id) {
        return paymentItemRepository.findById(id).orElse(null);
    }

    public void deletePayment(Long id) {
    	paymentItemRepository.deleteById(id);
    }

    
    @Transactional
    public PaymentItem createPayment(PaymentItemDto paymentDto, List<Long> serviceItemIds) {
        // Get patient
    	
    	 PaymentItem payment = new PaymentItem();
    	if ((Long) paymentDto.getPatientId() != null) {
    	    Patient patient = patientRepository.findById(paymentDto.getPatientId()).orElse(null);
    	    payment.setPatient(patient);
    	}  
    		
    	if(paymentDto.getUnreegisteredPatien()!="") {
    	    payment.setUnregisteredPatientName(paymentDto.getUnreegisteredPatien());
    	}

        // Get service items
        List<ServiceItem> serviceItems = serviceItemRepository.findAllById(serviceItemIds);

        if (serviceItems.isEmpty()) {
            throw new RuntimeException("No service items found for given IDs");
        }

        // Calculate total amount
        double total = serviceItems.stream()
                .mapToDouble(ServiceItem::getPrice)
                .sum();

        // Create payment
        payment.setServiceItems(serviceItems);
        payment.addTracking("CREATED", "Created this payment with a cost of : " + payment.getAmount());
        payment.setAmount(total);
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        payment.setStatus("PENDING");
        
        paymentRepository.save(payment);
       generateReceiptNumber(payment.getId());

        return payment;
    }

	public PaymentItem confirmPayment(Long id) {
		PaymentItem payment =	paymentItemRepository.findById(id).orElse(null);
		payment.setStatus("PAID");
        payment.addTracking("Condirm payment", "Confirm and collected  this payment with a Receipt of : " + payment.getReferenceNumber());

		return  paymentRepository.save(payment);
	}
	
	 private PaymentItem generateReceiptNumber(Long id) {
	        LocalDate today = LocalDate.now();
	        String datePart = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	        PaymentItem  payment = paymentRepository.findById(id).get();
	        String numberStr = String.format("%04d", payment.getId());

	     payment.setReferenceNumber( "RCLB" + datePart + "-" + numberStr);
	     return   paymentRepository.save(payment);

	    }
}
