package com.ppp.billing.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.Dto.MedicineDto;
import com.ppp.billing.model.Category;
import com.ppp.billing.model.Medicine;
import com.ppp.billing.model.Medicine.Location;
import com.ppp.billing.repository.CategoryRepository;
import com.ppp.billing.repository.MedicineRepository;
import com.ppp.billing.repository.StockRequestRepository;

@Service
public class MedicineService {

    private final AccountingService accountingService;
    private final StockRequestRepository stockRequestRepository;

    @Autowired 
    MedicineRepository medicineRepository;
    @Autowired 
    CategoryRepository categoryRepository;

    MedicineService(StockRequestRepository stockRequestRepository, AccountingService accountingService) {
        this.stockRequestRepository = stockRequestRepository;
        this.accountingService = accountingService;
    }
	
    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = medicineRepository.findAll();
        medicines.sort(Comparator.comparing(Medicine::getName));
        return medicines;
    }

    public Medicine saveMedicine(MedicineDto dto) {
        Medicine med = new Medicine();
        med.setCategory(categoryRepository.findById(dto.getCategory()).orElseThrow(null));
        med.setName(dto.getName());
        med.setDescription(dto.getDescription());
        
        // ✅ pricing
        med.setPurchasePrice(dto.getPurchasePrice());
        med.setPacketPrice(dto.getPacketPrice());
        med.setUnitPrice(dto.getUnitPrice());
        med.setUnitsPerPacket(dto.getUnitsPerPacket());
        
        med.setQuantity(dto.getQuantity());
        med.setThreshold(dto.getThreshold());
        med.setExpirationDate(dto.getExpiringDate());
        med.setStoreQuantity(dto.getQuantity());
        med.setPharmacyQuantity(0);
        med.setLocation(Medicine.Location.STORE);

        med.addTracking("CREATED", 
            String.format("Installed new medicine [%s], qty %d, packetPrice %.2f, unitPrice %.2f", 
                dto.getName(), dto.getQuantity(), dto.getPacketPrice(), dto.getUnitPrice()));

        return medicineRepository.save(med);
    }

    public Medicine getMedicineById(Long id) {
        return medicineRepository.findById(id).orElseThrow(null);
    }

    public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }

    public Iterable<Medicine> findByLocation(Location location) {
        return medicineRepository.findByLocation(location);
    }

    public Optional<Medicine> findById(Long medicineId) {
        return medicineRepository.findById(medicineId);
    }

    public void edit(Long id, Medicine updated) {
        Medicine med = medicineRepository.findById(id).orElseThrow(null);
        
        // store old values for tracking
        BigDecimal oldPacketPrice = med.getPacketPrice();
        BigDecimal oldUnitPrice = med.getUnitPrice();
        int oldQty = med.getQuantity();
        
        Category cat = categoryRepository.findById(updated.getCategory().getId()).orElseThrow(null);
        
        med.setName(updated.getName());
        med.setCode(updated.getCode());
        med.setDescription(updated.getDescription());
        med.setCategory(cat);
        med.setThreshold(updated.getThreshold());
        med.setExpirationDate(updated.getExpirationDate());
        
        // update pricing
        med.setPurchasePrice(updated.getPurchasePrice());
        med.setPacketPrice(updated.getPacketPrice());
        med.setUnitPrice(updated.getUnitPrice());
        med.setUnitsPerPacket(updated.getUnitsPerPacket());
        
        // update quantities
        med.setQuantity(updated.getQuantity());
        med.setStoreQuantity(updated.getQuantity() - med.getPharmacyQuantity());
        
        med.addTracking("EDITED", String.format(
            "Edited medicine [%s]: qty %d → %d, packetPrice %.2f → %.2f, unitPrice %.2f → %.2f", 
            med.getName(), oldQty, updated.getQuantity(),
            oldPacketPrice, updated.getPacketPrice(),
            oldUnitPrice, updated.getUnitPrice()
        ));
        
        medicineRepository.save(med);
    }

    public Optional<Medicine> findByNameAndLocation(String name, Location location) {
        return medicineRepository.findByNameAndLocation(name, location);
    }

    public List<Medicine> findByNameContainingIgnoreCase(String query) {
        return medicineRepository.findByNameContainingIgnoreCase(query);
    }
	
    public void addQuantity(Long id, long quantity) {
        Medicine med = medicineRepository.findById(id).orElseThrow(null);
        int newTotal = med.getQuantity() + (int) quantity;
        med.setStoreQuantity(med.getStoreQuantity() + (int) quantity);
        med.setQuantity(newTotal);

        med.addTracking("ADD_QUANTITY", 
            String.format("Added %d units to [%s]. New total: %d", 
                quantity, med.getName(), newTotal));

        medicineRepository.save(med);
    }
	
    public boolean existsByName(String name) {
        return medicineRepository.findByNameIgnoreCase(name).isPresent();
    }
}

