package com.ppp.billing.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppp.billing.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query("SELECT MAX(s.receiptNumber) FROM Sale s WHERE s.receiptNumber LIKE CONCAT('RCPT', :datePart, '%')")
	String findMaxReceiptNumberForDate(@Param("datePart") String datePart);
	
	
	  // 🟢 Total sales (count of items) per day
    @Query("SELECT SUM(i.quantity) FROM Sale s JOIN s.items i WHERE DATE(s.saleDate) = CURRENT_DATE")
    Long getTotalItemsSoldToday();

    // 🟢 Total revenue per day
    @Query("SELECT SUM(s.total) FROM Sale s WHERE DATE(s.saleDate) = CURRENT_DATE")
    BigDecimal getTotalRevenueToday();

    // 🟢 Total sales (count of items) per week
    @Query("SELECT SUM(i.quantity) FROM Sale s JOIN s.items i WHERE FUNCTION('YEARWEEK', s.saleDate) = FUNCTION('YEARWEEK', CURRENT_DATE)")
    Long getTotalItemsSoldThisWeek();

    // 🟢 Total sales (count of items) per month
    @Query("SELECT SUM(i.quantity) FROM Sale s JOIN s.items i WHERE MONTH(s.saleDate) = MONTH(CURRENT_DATE) AND YEAR(s.saleDate) = YEAR(CURRENT_DATE)")
    Long getTotalItemsSoldThisMonth();

    // 🟢 Total sales by a specific pharmacist
    @Query("SELECT SUM(s.total) FROM Sale s WHERE s.pharmacist.id = :pharmacistId")
    BigDecimal getTotalSalesByUser(@Param("pharmacistId") Long pharmacistId);

    // 🟢 List of sales by pharmacist
    List<Sale> findByPharmacistId(Long pharmacistId);
    
    @Query("SELECT COALESCE(SUM(s.total), 0) FROM Sale s WHERE DATE(s.saleDate) = :date")
    BigDecimal sumSalesByDate(@Param("date") Date date);

    @Query("SELECT s FROM Sale s WHERE DATE(s.saleDate) BETWEEN :sDate AND :eDate")
	List<Sale> findBySaleDateBetween(@Param("sDate")Date sDate, @Param("eDate")Date eDate);
       
    @Query("SELECT p.saleDate, SUM(p.total) " +
    	       "FROM Sale p " +
    	       "WHERE FUNCTION('MONTH', p.saleDate) = :month AND FUNCTION('YEAR', p.saleDate) = :year " +
    	       "GROUP BY p.saleDate ORDER BY p.saleDate")
    	List<Object[]> getDailyPharmacySales(@Param("month") int month, @Param("year") int year);

}
