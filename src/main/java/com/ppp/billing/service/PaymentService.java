package com.ppp.billing.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.Payment;
import com.ppp.billing.model.Staff;
import com.ppp.billing.repository.PaymentRepository;
import com.ppp.billing.repository.StaffRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	
	 private final PaymentRepository paymentRepository;
	    private final StaffRepository staffRepository;

	    public Payment payStaff(Long staffId, BigDecimal amount, LocalDate paymentDate) {
	        Staff staff = staffRepository.findById(staffId)
	                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + staffId));

	        Payment payment = new Payment();
	        payment.setStaff(staff);
	        payment.setAmount(amount);
	        payment.setPaymentDate(paymentDate);

	        return paymentRepository.save(payment);
	    }

	    public Payment updatePayment(Long paymentId, Payment updatedPayment) {
	        return paymentRepository.findById(paymentId)
	                .map(payment -> {
	                    payment.setAmount(updatedPayment.getAmount());
	                    payment.setPaymentDate(updatedPayment.getPaymentDate());
	                    if (updatedPayment.getStaff() != null) {
	                        payment.setStaff(updatedPayment.getStaff());
	                    }
	                    return paymentRepository.save(payment);
	                })
	                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + paymentId));
	    }

	    public List<Payment> getAllPayments() {
	        return paymentRepository.findAll();
	    }

	    public List<Payment> getPaymentsByStaffId(Long staffId) {
	        return paymentRepository.findByStaffId(staffId);
	    }

	    public void deletePayment(Long paymentId) {
	        paymentRepository.deleteById(paymentId);
	    }

	   public BigDecimal getTotalPaymentsForMonth(int year, int month) {
	        return paymentRepository.calculateTotalPaymentsForMonth(year, month);
	    }

	    
	    
}
