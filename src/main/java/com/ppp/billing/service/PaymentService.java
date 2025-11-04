package com.ppp.billing.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.ppp.billing.Dto.PaymentDTO;
import com.ppp.billing.model.Payment;

public interface PaymentService {
	//Payment savePayment(Payment payment);
    Payment getPaymentById(Long id);
    List<Payment> getPaymentsByFacture(Long factureId);
    List<Payment> getAllPayments();
    void deletePayment(Long id);
	Payment savePayment(Long factureId, Payment payment);
	Payment savePaymentFacture(PaymentDTO dto) throws IOException;
	Payment findById(Long paymentId);
	BigDecimal getTodaysPayment();
	
	
}
