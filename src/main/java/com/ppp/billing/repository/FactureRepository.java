package com.ppp.billing.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppp.billing.model.Facture;

public interface FactureRepository  extends JpaRepository<Facture, Long>{
    Facture findByVisitId(Long visitId);
    List<Facture> findByVisit_Patient_IdOrderByIdDesc(Long id);
    List<Facture> findByCreatedDateBetweenOrderByIdDesc(LocalDate start, LocalDate end);
    List<Facture> findAllByOrderByIdDesc();
	List<Facture> findByVisit_Patient_NameContainingIgnoreCaseOrderByIdDesc(String patientName);
	
	@Query("SELECT f.referenceNumber, " +
		       "COALESCE(p.name, f.customerName), " +
		       "COALESCE(sub.name, 'Pharmacy Sales'), " +
		       "f.netAmount, f.amountPaid, f.discount, f.status " +
		       "FROM Facture f " +
		       "LEFT JOIN f.visit v " +
		       "LEFT JOIN v.patient p " +
		       "LEFT JOIN v.subtypes sub " +
		       "WHERE f.createdDate = :date")
		List<Object[]> findFacturesWithSubtypesByDate(@Param("date") LocalDate date);
		
		 @Query("SELECT COUNT(f) FROM Facture f " +
		           "WHERE f.status = 'PENDING' AND f.createdDate = CURRENT_DATE")
		 			Long getTotalPendingBills();
		
		@Query("SELECT COUNT(f) FROM Facture f " +
			       "WHERE f.status = 'PENDING' " +
			       "AND MONTH(f.createdDate) = MONTH(CURRENT_DATE) " +
			       "AND YEAR(f.createdDate) = YEAR(CURRENT_DATE)")
			Long getTotalPendingBillsThisMonth();
		
		 @Query("SELECT SUM(f.amountPaid) FROM Facture f WHERE f.createdDate = CURRENT_DATE")
		    BigDecimal getTotalRevenueToday();

		    // Total revenue collected this month
		    @Query("SELECT SUM(f.amountPaid) FROM Facture f " +
		           "WHERE MONTH(f.createdDate) = MONTH(CURRENT_DATE) " +
		           "AND YEAR(f.createdDate) = YEAR(CURRENT_DATE) ")
		    BigDecimal getTotalRevenueThisMonth();

		    // Paid bills count today (fully paid)
		    @Query("SELECT COUNT(f) FROM Facture f WHERE f.fullyPaid = true AND f.createdDate = CURRENT_DATE")
		    Long getPaidBillsToday();

		    // Paid bills count this month (fully paid)
		    @Query("SELECT COUNT(f) FROM Facture f " +
		           "WHERE f.fullyPaid = true " +
		           "AND MONTH(f.createdDate) = MONTH(CURRENT_DATE) " +
		           "AND YEAR(f.createdDate) = YEAR(CURRENT_DATE)")
		    Long getPaidBillsThisMonth();

}
