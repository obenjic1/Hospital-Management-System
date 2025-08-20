package com.ppp.billing.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ppp.billing.model.Payment;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	List<Payment> findByStaffId(Long staffId);

	
	@Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE YEAR(p.paymentDate) = :year AND MONTH(p.paymentDate) = :month")
	BigDecimal calculateTotalPaymentsForMonth(int year, int month);

	
}
