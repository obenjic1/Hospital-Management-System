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
	
	@Query("SELECT " +
		       "f.referenceNumber, " +
		       "COALESCE(p.name, COALESCE(f.customerName, 'N/A')), " +
		       "CASE WHEN sub.name IS NOT NULL THEN sub.name ELSE 'Pharmacy Sales' END, " +
		       "COALESCE(SUM(s.total), COALESCE(MAX(f.netAmount), 0)), " +
		       "COALESCE(SUM(f.amountPaid), 0), " +
		       "COALESCE(MAX(f.discount), 0), " +
		       "CASE WHEN COALESCE(SUM(f.amountPaid), 0) >= COALESCE(SUM(s.total), COALESCE(MAX(f.netAmount), 0)) " +
		       "THEN 'PAID' ELSE 'PENDING' END " +
		       "FROM Facture f " +
		       "LEFT JOIN f.sales s " +
		       "LEFT JOIN f.visit v " +
		       "LEFT JOIN v.patient p " +
		       "LEFT JOIN v.subtypes sub " +
		       "WHERE f.createdDate = :date " +
		       "GROUP BY f.referenceNumber, " +
		       "COALESCE(p.name, COALESCE(f.customerName, 'N/A')), " +
		       "CASE WHEN sub.name IS NOT NULL THEN sub.name ELSE 'Pharmacy Sales' END")
		List<Object[]> findFacturesWithSubtypesOrSalesByDate(@Param("date") LocalDate date);

		@Query("SELECT " +
			       "f.referenceNumber, " +
			       "COALESCE(p.name, f.customerName), " +
			       "CASE WHEN sub.name IS NOT NULL THEN 'Consultation' ELSE 'Pharmacy Sales' END, " +
			       "COALESCE(sub.name, i.description), " +
			       "COALESCE(sub.price, i.price), " +
			       "COALESCE(i.quantity, 1), " +
			       "(COALESCE(sub.price, i.price) * COALESCE(i.quantity, 1)), " +  // ✅ no CAST
			       "COALESCE(f.amountPaid, 0), " +
			       "f.discount, " +
			       "f.status " +
			       "FROM Facture f " +
			       "LEFT JOIN f.visit v " +
			       "LEFT JOIN v.patient p " +
			       "LEFT JOIN v.subtypes sub " +
			       "LEFT JOIN f.items i " +
			       "WHERE f.createdDate = :date")
			List<Object[]> findSalesReportByDate(@Param("date") LocalDate date);

		
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

		    
		    
		    // ✅ Total PAID revenue for today
		    @Query("SELECT COALESCE(SUM(f.amountPaid), 0) " +
		           "FROM Facture f WHERE f.createdDate = CURRENT_DATE")
		    BigDecimal getTotalPaidToday();

		    // ✅ Total EXPECTED (net amount) for today (paid + pending)
		    @Query("SELECT COALESCE(SUM(f.netAmount), 0) " +
		           "FROM Facture f WHERE f.createdDate = CURRENT_DATE")
		    BigDecimal getTotalExpectedToday();

		    // ✅ Total PENDING (unpaid) for today
		    @Query("SELECT COALESCE(SUM(f.netAmount - COALESCE(f.amountPaid, 0)), 0) " +
		           "FROM Facture f WHERE f.createdDate = CURRENT_DATE")
		    BigDecimal getTotalPendingToday();


		    // ✅ Total PAID revenue this month
		    @Query("SELECT COALESCE(SUM(f.amountPaid), 0) " +
		           "FROM Facture f " +
		           "WHERE MONTH(f.createdDate) = MONTH(CURRENT_DATE) " +
		           "AND YEAR(f.createdDate) = YEAR(CURRENT_DATE)")
		    BigDecimal getTotalPaidThisMonth();

		    // ✅ Total EXPECTED this month
		    @Query("SELECT COALESCE(SUM(f.netAmount), 0) " +
		           "FROM Facture f " +
		           "WHERE MONTH(f.createdDate) = MONTH(CURRENT_DATE) " +
		           "AND YEAR(f.createdDate) = YEAR(CURRENT_DATE)")
		    BigDecimal getTotalExpectedThisMonth();

		    // ✅ Total PENDING this month
		    @Query("SELECT COALESCE(SUM(f.netAmount - COALESCE(f.amountPaid, 0)), 0) " +
		           "FROM Facture f " +
		           "WHERE MONTH(f.createdDate) = MONTH(CURRENT_DATE) " +
		           "AND YEAR(f.createdDate) = YEAR(CURRENT_DATE)")
		    BigDecimal getTotalPendingThisMonth();
}
