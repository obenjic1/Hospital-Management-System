package com.ppp.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.ServiceType;

public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long>{
    List<ServiceType> findByCategoryId(Long categoryId);

}
