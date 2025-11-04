package com.ppp.billing.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppp.billing.model.Payment;

public interface PaymentRepository  extends JpaRepository <Payment, Long>{
    List<Payment> findByFactureId(Long factureId);
    
    
    @Query("SELECT SUM(p.amountPaid) FROM Payment p " +
    	       "WHERE DATE(p.paymentDate) = CURRENT_DATE")
    	BigDecimal getTotalRevenueToday();
    
    
    @Query("SELECT SUM(p.amountPaid) FROM Payment p " +
    	       "WHERE p.paymentDate BETWEEN :start AND :end")
    	BigDecimal getTotalRevenue(@Param("start") LocalDateTime start,
    	                           @Param("end") LocalDateTime end);


}
