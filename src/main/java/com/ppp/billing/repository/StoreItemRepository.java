package com.ppp.billing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.StoreItem;

public interface StoreItemRepository extends JpaRepository<StoreItem, Long> {
    Optional<StoreItem> findByMedicineId(Long medId);
    Optional<StoreItem> findByMedicineName(String name);

}
