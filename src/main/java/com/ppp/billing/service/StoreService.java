package com.ppp.billing.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.Dto.MedicineDto;
import com.ppp.billing.Dto.StoreStats;
import com.ppp.billing.model.Medicine;
import com.ppp.billing.model.Medicine.Location;
import com.ppp.billing.repository.CategoryRepository;
import com.ppp.billing.repository.MedicineRepository;

@Service
public class StoreService {
	
	
	   private final MedicineService medicineService;
	   @Autowired
	   MedicineRepository medicineRepository;
	   
	   @Autowired
	   CategoryRepository categoryRepository;

	    public StoreService(MedicineService medicineService) {
	        this.medicineService = medicineService;
	    }

	    public Medicine addMedicine(MedicineDto medicine) {
	        return medicineService.saveMedicine(medicine);
	    }

	    public Iterable<Medicine> getStoreMedicines() {
	        return medicineService.findByLocation(Medicine.Location.STORE);
	    }

	    @Transactional
	    public void transferToPharmacy(Long medicineId, int quantity) throws Exception {
	    	Medicine  storeMedicine = medicineService.findById(medicineId)
	                .orElseThrow(() -> new RuntimeException("Medicine not found"));

	        if (storeMedicine.getLocation() != Medicine.Location.STORE) {
	            throw new RuntimeException("Medicine is not in store");
	        }

	        if (storeMedicine.getQuantity() < quantity) {
	            throw new RuntimeException("Not enough stock in store");
	        }

	        // Reduce from store
	        storeMedicine.setStoreQuantity(storeMedicine.getStoreQuantity() - quantity);
	        storeMedicine.setPharmacyQuantity(storeMedicine.getPharmacyQuantity() + quantity);
	        medicineService.edit(storeMedicine.getId(),storeMedicine);
	     
	        // Add to pharmacy
	      
	        
//	        Optional<Medicine> pharmacyMedicine = medicineService.findByNameAndLocation(storeMedicine.getName(), Medicine.Location.PHARMACY);
//	        if(pharmacyMedicine.isPresent()) {
//	        	 pharmacyMedicine.get().setPharmacyQuantity( (pharmacyMedicine.get().getQuantity() + quantity));     
//		        medicineService.edit(pharmacyMedicine.get().getId(),pharmacyMedicine.get());
//
//	        }
	    }
	    
	    
	    public StoreStats computeStoreStats(int expiringWithinDays) {
	        long totalMedicines = medicineRepository.countStoreMedicines();
	        long totalQuantity = medicineRepository.sumStoreQuantity();
	        BigDecimal totalValue = medicineRepository.sumStoreValue();
	        LocalDate before = LocalDate.now().plusDays(expiringWithinDays);
	        long expiringSoon = medicineRepository.countStoreExpiringBefore(before);

	        return new StoreStats(totalMedicines, totalQuantity, totalValue, expiringSoon);
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
	            return medicineRepository.findByCategory_NameAndNameContainingIgnoreCase(categoryFilter, searchQuery);
	        }
	    }

	    public List<String> listCategoriesInStore() {
	        return medicineRepository.findDistinctCategoryNamesByLocation(Location.STORE);
	    }

	    // transferToPharmacy(...) method you alre
	    
	    

}
