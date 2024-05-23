package com.ppp.billing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.BindingType;


public interface BindingTypeRepository extends JpaRepository<BindingType, Long> {

	Optional<BindingType>  findByName(String name);
	Optional<BindingType>  findById(int id);

}
