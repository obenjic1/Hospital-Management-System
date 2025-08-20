package com.ppp.billing.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ppp.billing.Dto.PharmacyStats;
import com.ppp.billing.Dto.StoreStats;
import com.ppp.billing.model.Medicine;
import com.ppp.billing.model.Medicine.Location;
import com.ppp.billing.repository.MedicineRepository;
@Service
public class PharmacyService {
	 private final MedicineRepository medicineRepository;

	    public PharmacyService(MedicineRepository medicineRepository) {
	        this.medicineRepository = medicineRepository;
	    }

	    public List<Medicine> getAllMedicines() {
	        return medicineRepository.findAll();
	    }

	
	    public PharmacyStats computePharmacyStats(int expiringWithinDays) {
	        long totalMedicines = medicineRepository.countPharmacyMedicines();
	        long totalQuantity = medicineRepository.sumPharmacyQuantity();
	        
	        BigDecimal totalValue = medicineRepository.sumPharmacyValue();
	        LocalDate before = LocalDate.now().plusDays(expiringWithinDays);
	        long expiringSoon = medicineRepository.countPharmacyExpiringBefore(before);
	        long lowStock = medicineRepository.countLowStockMedicines();

	        return new PharmacyStats(totalMedicines, totalQuantity, totalValue, expiringSoon, lowStock);
	    }
	    
	    public List<Medicine> listStoreMedicines(String categoryFilter, String searchQuery) {
	        Location loc = Location.STORE;
	        if ((categoryFilter == null || categoryFilter.isEmpty() || categoryFilter.equals("All"))
	                && (searchQuery == null || searchQuery.isEmpty())) {
	            return medicineRepository.findByLocation(loc);
	        } else if ((categoryFilter == null || categoryFilter.isEmpty() || categoryFilter.equals("All"))) {
	            return medicineRepository.findByLocationAndNameContainingIgnoreCase(loc, searchQuery);
	        } else if (searchQuery == null || searchQuery.isEmpty()) {
	            return medicineRepository.findByLocationAndCategory_Name(loc, categoryFilter);
	        } else {
	            return medicineRepository.findByCategory_NameAndNameContainingIgnoreCase( categoryFilter, searchQuery);
	        }
	    }
	 

}
