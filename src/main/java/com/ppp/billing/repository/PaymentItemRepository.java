package com.ppp.billing.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ppp.billing.model.PaymentItem;

@Repository
public interface PaymentItemRepository extends JpaRepository<PaymentItem, Long>{
	
	@Query("SELECT COALESCE(SUM(p.amount), 0) FROM PaymentItem p WHERE DATE(p.paymentDate) = :date")
    BigDecimal sumPaymentsByDate(@Param("date") Date date);

	@Query("SELECT pay.paymentDate, SUM(pay.amount) " +
		       "FROM PaymentItem pay " +
		       "WHERE FUNCTION('MONTH', pay.paymentDate) = :month AND FUNCTION('YEAR', pay.paymentDate) = :year " +
		       "GROUP BY pay.paymentDate ORDER BY pay.paymentDate")
		List<Object[]> getDailyServicePayments(@Param("month") int month, @Param("year") int year);

//		@Query("SELECT MAX(p.referenceNumber) FROM PaymentItem p WHERE p.referenceNumber LIKE CONCAT('RCLB', :datePart, '%')")
//		String findMaxReceiptNumberForDate(@Param("datePart") String datePart);
		
}
