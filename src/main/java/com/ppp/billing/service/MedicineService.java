package com.ppp.billing.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import com.ppp.billing.repository.StockRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.Dto.MedicineDto;
import com.ppp.billing.model.Category;
import com.ppp.billing.model.Medicine;
import com.ppp.billing.model.Staff;
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
		medicines.sort(Comparator.comparing(Medicine::getName));
		
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
		med.addTracking("CREATED", "install new medicine with quantity of " + medicine.getQuantity());
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
		long  q = med.getQuantity();
		BigDecimal  p= med.getPrice();

		Category cat =  categoryRepository.findById(medicine.getCategory().getId()).get();
		med.setPrice(medicine.getPrice());
		med.setQuantity(medicine.getQuantity());
		med.addTracking("Edited", "edited medicine with changes former in formation " + "name " +
		med.getName() +" expiration date :" + med.getExpirationDate() + " quantity :" + med.getQuantity() + " price " + p +" to : " + med.getPrice() + " qty : "+ med.getQuantity() );
		med.setCategory(cat);
		med.setName(medicine.getName());
		med.setDescription(medicine.getDescription());
		
		med.setThreshold(medicine.getThreshold());
		med.setStoreQuantity(medicine.getQuantity()- med.getPharmacyQuantity());
		med.setExpirationDate(medicine.getExpirationDate());
	//	med.setPharmacyQuantity(medicine.getPharmacyQuantity());
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
		med.addTracking("ADD", "Added  medicine with quantity of " + quantity + " tola was " + med.getQuantity());


		medicineRepository.save(med);
	}
	
	public boolean existsByName(String name) {
        return medicineRepository.findByNameIgnoreCase(name).isPresent();
    }
	
}
