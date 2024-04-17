package com.ppp.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.BindingType;


public interface BindingTypeRepository extends JpaRepository<BindingType, Long> {

	BindingType findByName(String name);

}
