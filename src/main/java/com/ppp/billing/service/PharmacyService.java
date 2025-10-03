package com.ppp.billing.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ppp.billing.Dto.SaleDTO;
import com.ppp.billing.Dto.SaleItemDTO;
import com.ppp.billing.model.Medicine;
import com.ppp.billing.model.Medicine.Location;
import com.ppp.billing.model.PaymentMethod;
import com.ppp.billing.model.Sale;
import com.ppp.billing.model.SaleItem;
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
