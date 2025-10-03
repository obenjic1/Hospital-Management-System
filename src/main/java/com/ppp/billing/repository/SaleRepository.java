package com.ppp.billing.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppp.billing.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	

	@Query("SELECT s.saleDate, SUM(s.totalAmount) " +
		       "FROM Sale s " +
		       "WHERE s.saleDate = :today " +
		       "GROUP BY s.saleDate")
		List<Object[]> findRevenueForDate(@Param("today") LocalDate today);



    // Sales detail for one date
    List<Sale> findBySaleDate(LocalDate saleDate);

	// amount sold for that day
	
    @Query("SELECT COALESCE(SUM(s.totalAmount), 0) FROM Sale s WHERE s.saleDate = :date")
    BigDecimal getTotalSalesByDate(@Param("date") LocalDate date);
	

    
    @Query("SELECT COALESCE(SUM(s.total), 0) FROM Sale s WHERE DATE(s.saleDate) = :date")
    BigDecimal sumSalesByDate(@Param("date") Date date);

    @Query("SELECT s FROM Sale s WHERE DATE(s.saleDate) BETWEEN :sDate AND :eDate")
	List<Sale> findBySaleDateBetween(@Param("sDate")Date sDate, @Param("eDate")Date eDate);
       
    @Query("SELECT p.saleDate, SUM(p.total) " +
    	       "FROM Sale p " +
    	       "WHERE FUNCTION('MONTH', p.saleDate) = :month AND FUNCTION('YEAR', p.saleDate) = :year " +
    	       "GROUP BY p.saleDate ORDER BY p.saleDate")
    	List<Object[]> getDailyPharmacySales(@Param("month") int month, @Param("year") int year);
    	
    	@Query("SELECT COALESCE(SUM(i.quantity),0) FROM Sale s JOIN s.items i WHERE DATE(s.saleDate) = :d")
    	Long getTotalItemsSoldByDate(@Param("d") java.sql.Date d);

}
