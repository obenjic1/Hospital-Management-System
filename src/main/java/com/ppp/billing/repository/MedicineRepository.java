package com.ppp.billing.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppp.billing.model.Medicine;
import com.ppp.billing.model.Medicine.Location;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    Optional<Medicine> findByName(String name);
    List<Medicine> findByNameContainingIgnoreCase(String q);
    List<Medicine> findByLocation(Location location);
	Optional<Medicine> findByNameAndLocation(String name, Location pharmacy);
	List<Medicine> findByLocationAndNameContainingIgnoreCase(Location location, String q);
	List<Medicine> findByLocationAndCategory_Name(Location location, String categoryName);
	List<Medicine> findByCategory_NameAndNameContainingIgnoreCase(String categoryName, String q);
    Optional<Medicine> findByNameIgnoreCase(String name);
	
	
	 @Query("SELECT COUNT(m) FROM Medicine m WHERE m.location = :loc")
	    long countByLocation(@Param("loc") Location loc);

	    @Query("SELECT COALESCE(SUM(m.quantity), 0) FROM Medicine m WHERE m.location = :loc")
	    long sumQuantityByLocation(@Param("loc") Location loc);

	    @Query("SELECT COALESCE(SUM(m.price * m.quantity), 0) FROM Medicine m WHERE m.location = :loc")
	    BigDecimal sumValueByLocation(@Param("loc") Location loc);

	    // Expiring soon: before a date
	    @Query("SELECT COUNT(m) FROM Medicine m WHERE m.location = :loc AND m.expirationDate <= :beforeDate")
	    long countExpiringBefore(@Param("loc") Location loc, @Param("beforeDate") LocalDate beforeDate);

	    // Categories (if Category is an entity)
	    @Query("SELECT DISTINCT m.category.name FROM Medicine m WHERE m.location = :loc")
	    List<String> findDistinctCategoryNamesByLocation(@Param("loc") Location loc);
	    
	    
	    @Query("SELECT COUNT(m) FROM Medicine m WHERE m.storeQuantity > 0")
	    long countStoreMedicines();

	    // Count medicines available in pharmacy (quantity > 0)
	    @Query("SELECT COUNT(m) FROM Medicine m ")
	    long countPharmacyMedicines();

	    // Sum of store quantities
	    @Query("SELECT COALESCE(SUM(m.storeQuantity), 0) FROM Medicine m")
	    long sumStoreQuantity();

	    // Sum of pharmacy quantities
	    @Query("SELECT COALESCE(SUM(m.pharmacyQuantity), 0) FROM Medicine m")
	    long sumPharmacyQuantity();

	    // Total value in store
	    @Query("SELECT COALESCE(SUM(m.price * m.storeQuantity), 0) FROM Medicine m")
	    BigDecimal sumStoreValue();

	    // Total value in pharmacy
	    @Query("SELECT COALESCE(SUM(m.price * m.pharmacyQuantity), 0) FROM Medicine m")
	    BigDecimal sumPharmacyValue();

	    // Count medicines expiring soon in store
	    @Query("SELECT COUNT(m) FROM Medicine m WHERE m.storeQuantity > 0 AND m.expirationDate <= :beforeDate")
	    long countStoreExpiringBefore(@Param("beforeDate") LocalDate beforeDate);

	    // Count medicines expiring soon in pharmacy
	    @Query("SELECT COUNT(m) FROM Medicine m WHERE m.pharmacyQuantity > 0 AND m.expirationDate <= :beforeDate")
	    long countPharmacyExpiringBefore(@Param("beforeDate") LocalDate beforeDate);
	    
	    
	 // Count low stock
	    @Query("SELECT COUNT(m) FROM Medicine m WHERE m.pharmacyQuantity <= m.threshold")
	    long countLowStockMedicines();

	    // List low stock medicines
	    @Query("SELECT m FROM Medicine m WHERE m.quantity <= m.threshold")
	    List<Medicine> findLowStockMedicines();
	    
	    // Distinct categories in store
	    @Query("SELECT DISTINCT m.category.name FROM Medicine m WHERE m.storeQuantity > 0")
	    List<String> findDistinctStoreCategories();

	    // Distinct categories in pharmacy
	    @Query("SELECT DISTINCT m.category.name FROM Medicine m WHERE m.pharmacyQuantity > 0")
	    List<String> findDistinctPharmacyCategories();
	    
	    // Expired medicines
	    @Query("SELECT m FROM Medicine m WHERE m.expirationDate < CURRENT_DATE")
	    List<Medicine> findExpiredMedicines();
	 // Medicines expiring within the next 30 days
	    @Query("SELECT m FROM Medicine m WHERE m.expirationDate BETWEEN CURRENT_DATE AND CURRENT_DATE + 30")
	    List<Medicine> findMedicinesExpiringSoon();
	    
	    //  Out of stock medicines
	    @Query("SELECT m FROM Medicine m WHERE m.pharmacyQuantity = 0")
	    List<Medicine> findOutOfStockMedicines();
}
