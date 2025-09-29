package com.ppp.billing.service;

import java.util.List;

import com.ppp.billing.model.Payment;

public interface PaymentService {
	//Payment savePayment(Payment payment);
    Payment getPaymentById(Long id);
    List<Payment> getPaymentsByFacture(Long factureId);
    List<Payment> getAllPayments();
    void deletePayment(Long id);
	Payment savePayment(Long factureId, Payment payment);
}
