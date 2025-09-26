package com.ppp.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.Payment;

public interface PaymentRepository  extends JpaRepository <Payment, Long>{
    List<Payment> findByFactureId(Long factureId);

}
