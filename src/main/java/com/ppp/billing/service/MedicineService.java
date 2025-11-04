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

    private final StockRequestRepository stockRequestRepository;

    @Autowired
    MedicineRepository medicineRepository;
    
    @Autowired
    CategoryRepository categoryRepository;

    MedicineService(StockRequestRepository stockRequestRepository) {
        this.stockRequestRepository = stockRequestRepository;
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
        med.setCode(dto.getCode().toUpperCase());
        med.setDescription(dto.getDescription());
        med.setPurchasePrice(dto.getPurchasePrice());
        med.setPacketPrice(dto.getPacketPrice());
        med.setUnitPrice(dto.getUnitPrice());
        med.setUnitsPerPacket(dto.getUnitsPerPacket());
        med.setQuantity(dto.getQuantity());  // Frontend will provide the quantity
        med.setThreshold(dto.getThreshold());
        med.setExpirationDate(dto.getExpiringDate());
        med.setLocation(Medicine.Location.STORE);
        
        // Ensure totals are updated after setting quantity
        med.updateTotals();

        med.addTracking("CREATED", 
            String.format("Installed new medicine [%s], qty %d, packetPrice %.2f, unitPrice %.2f", 
                dto.getName(), dto.getQuantity(), dto.getPacketPrice(), dto.getUnitPrice()));
        
        try {
            medicineRepository.save(med);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
      
        return med;
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

    public void edit(Long id, MedicineDto medicine) {
        Medicine med = medicineRepository.findById(id).orElseThrow(null);
        
        // Store old values for tracking
        BigDecimal oldPacketPrice = med.getPacketPrice();
        BigDecimal oldUnitPrice = med.getUnitPrice();
        int oldQty = med.getQuantity();

        Category cat = categoryRepository.findById(medicine.getCategory()).orElseThrow(null);
        
        // Update basic fields
        med.setName(medicine.getName());
        med.setCode(medicine.getCode());
        med.setDescription(medicine.getDescription());
        med.setCategory(cat);
        med.setThreshold(medicine.getThreshold());
        med.setExpirationDate(medicine.getExpiringDate());
        
        // Update pricing
        med.setPurchasePrice(medicine.getPurchasePrice());
        med.setPacketPrice(medicine.getPacketPrice());
        med.setUnitPrice(medicine.getUnitPrice());
        med.setUnitsPerPacket(medicine.getUnitsPerPacket());
        
        // Update quantities
        med.editQuantity(medicine.getQuantity());  // Uses new method to update quantities
        
        // Recalculate totals based on new quantity
        med.updateTotals();

        med.addTracking("EDITED", String.format(
            "Edited medicine [%s]: qty %d → %d, packetPrice %.2f → %.2f, unitPrice %.2f → %.2f", 
            med.getName(), oldQty, medicine.getQuantity(),
            oldPacketPrice, medicine.getPacketPrice(),
            oldUnitPrice, medicine.getUnitPrice()
        ));

        medicineRepository.save(med);
    }

    public Optional<Medicine> findByNameAndLocation(String name, Location location) {
        return medicineRepository.findByNameAndLocation(name, location);
    }

    public List<Medicine> findByNameContainingIgnoreCase(String query) {
        return medicineRepository.findByNameContainingIgnoreCase(query);
    }

    public void addQuantity(Long id, int quantity) {
        Medicine med = medicineRepository.findById(id).orElseThrow(null);
        
        // Use the new method to add stock
        med.addQuantity(quantity);

        // Recalculate totals after adding
        med.updateTotals();
        
        med.addTracking("ADD_QUANTITY", 
            String.format("Added %d units to [%s]. New total: %d", 
                quantity, med.getName(), med.getTotalUnitsQuantity() / med.getUnitsPerPacket()));

        medicineRepository.save(med);
    }

    public boolean existsByName(String name) {
        return medicineRepository.findByNameIgnoreCase(name).isPresent();
    }
    
    public List<Medicine> getExpiredMeds() {
        return medicineRepository.findExpiredMedicines();
    }

    public List<Medicine> getSoonExpiredMeds() {
        return medicineRepository.findMedicinesExpiringSoon();
    }

    public List<Medicine> getLowStockMeds() {
        return medicineRepository.findLowStockMedicines();
    }

    public List<Medicine> getOutOfStockMeds() {
        return medicineRepository.findOutOfStockMedicines();
    }

	public void save(Medicine storeMedicine) {
		
		medicineRepository.save(storeMedicine);
	}
}
