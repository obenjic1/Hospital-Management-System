package com.ppp.billing.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.PharmacyItem;


public interface PharmacyItemRepository extends JpaRepository<PharmacyItem, Long> {
    Optional<PharmacyItem> findByMedicineId(Long medId);
    List<PharmacyItem> findByExpiryDateBefore(LocalDate date);
    Optional<PharmacyItem> findByMedicineName(String name);

}
