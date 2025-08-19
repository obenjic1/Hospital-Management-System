package com.ppp.billing.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import com.ppp.billing.repository.StockRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.Dto.MedicineDto;
import com.ppp.billing.model.Category;
import com.ppp.billing.model.Medicine;
import com.ppp.billing.model.Medicine.Location;
import com.ppp.billing.repository.CategoryRepository;
import com.ppp.billing.repository.MedicineRepository;

@Service
public class MedicineService {

    private final AccountingService accountingService;

    private final StockRequestRepository stockRequestRepository;

	@Autowired MedicineRepository medicineRepository;
	@Autowired CategoryRepository categoryRepository;

    MedicineService(StockRequestRepository stockRequestRepository, AccountingService accountingService) {
        this.stockRequestRepository = stockRequestRepository;
        this.accountingService = accountingService;
    }
	
	public List<Medicine> getAllMedicines() {
		List<Medicine> medicines = medicineRepository.findAll();
		return medicines;
	}
	public Medicine saveMedicine(MedicineDto medicine) {
		Medicine med = new Medicine();
		med.setCategory(categoryRepository.findById(medicine.getCategory()).get());
		med.setName(medicine.getName());
		med.setDescription(medicine.getDescription());
		med.setPrice(medicine.getPrice());
		med.setQuantity(medicine.getQuantity());
		med.setThreshold(medicine.getThreshold());
		med.setExpirationDate(medicine.getExpiringDate());

		med.setStoreQuantity(medicine.getQuantity());
		med.setPharmacyQuantity(0);
		med.setLocation(Medicine.Location.STORE);
		medicineRepository.save(med);

	   return med;
		
	}
	public Medicine getMedicineById(Long id) {
		
		return medicineRepository.findById(id).get();
	}
	public void deleteMedicine(Long id) {
		medicineRepository.deleteById(id);
	}
	public Iterable<Medicine> findByLocation(Location location) {
		
		return medicineRepository.findByLocation(location);
	
	}
	public  Optional<Medicine> findById(Long medicineId) {
		return medicineRepository.findById(medicineId);
	}
	public void edit(Long id,Medicine medicine) {
		Medicine med = medicineRepository.findById(id).get();
		Category cat =  categoryRepository.findById(medicine.getCategory().getId()).get();
		med.setCategory(cat);
		med.setName(medicine.getName());
		med.setDescription(medicine.getDescription());
		med.setPrice(medicine.getPrice());
		med.setQuantity(medicine.getQuantity());
		med.setThreshold(medicine.getThreshold());
		med.setStoreQuantity(medicine.getStoreQuantity());
		med.setExpirationDate(medicine.getExpirationDate());
		med.setPharmacyQuantity(medicine.getPharmacyQuantity());
		medicineRepository.save(med);
		
	}
	public Optional<Medicine> findByNameAndLocation(String name, Location location) {
		Medicine meds = new Medicine();
	if( medicineRepository.findByNameAndLocation(name, location).get() != null){
		meds = medicineRepository.findByNameAndLocation(name, location).get();
	}
		return Optional.ofNullable(meds);
	}
	public List<Medicine> findByNameContainingIgnoreCase(String query) {
	
		return medicineRepository.findByNameContainingIgnoreCase(query);
	}
	
	public void addQuantity(Long id, long quantity) {
		Medicine med = medicineRepository.findById(id).get();
		int total = med.getQuantity() + (int)quantity;
		med.setStoreQuantity(med.getStoreQuantity() + (int)quantity);
		med.setQuantity(total);

		medicineRepository.save(med);
	}
	
	public boolean existsByName(String name) {
        return medicineRepository.findByNameIgnoreCase(name).isPresent();
    }
	
}
