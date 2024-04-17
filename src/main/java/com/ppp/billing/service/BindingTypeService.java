package com.ppp.billing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.BindingType;
import com.ppp.billing.model.dto.BindingTypeDTO;



@Service
public interface BindingTypeService { 
	
	BindingType findByName(String name);
	String updateBindingType (BindingTypeDTO updatbindingTypeDto, Long id);
	BindingType findById(Long id);
	List<BindingType> getAllBindingTypes();
	String addBindingType (BindingTypeDTO bindingTypeDto);
	void deleteById(Long id);

}
