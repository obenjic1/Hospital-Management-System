package com.ppp.billing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.BindingType;
import com.ppp.billing.model.dto.BindingTypeDTO;



@Service
public interface BindingTypeService { 
	
	Optional<BindingType> findByName(String name);
	BindingType update (BindingTypeDTO updatbindingTypeDto, long id);
	Optional<BindingType> findById(long id);
	List<BindingType> findAll();
	BindingType save (BindingTypeDTO bindingTypeDto);
	void delete(long id);

}
