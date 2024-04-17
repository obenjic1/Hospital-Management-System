package com.ppp.billing.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.BindingType;
import com.ppp.billing.model.dto.BindingTypeDTO;
import com.ppp.billing.repository.BindingTypeRepository;
import com.ppp.billing.service.BindingTypeService;

@Service
public class BindingTypeserviceImpl implements BindingTypeService {

	@Autowired
	BindingTypeRepository bindingTyperepository;
	
	@Override
	public BindingType findByName(String name) {
		return bindingTyperepository.findByName(name);
	}

	@Override
	public String updateBindingType(BindingTypeDTO updatbindingTypeDto, Long id) {
		Optional<BindingType> bindingType = bindingTyperepository.findById(id);
        if (bindingType.isPresent()) {
        	BindingType updatedBindingType = bindingType.get();
        	updatedBindingType.setName(updatbindingTypeDto.getName());
        	bindingTyperepository.save(updatedBindingType);
		return "Binding Type Updated";}
        return "update not successfull";
	}

	@Override
	public BindingType findById(Long id) {
		Optional<BindingType> bindingType = bindingTyperepository.findById(id);
		return bindingType.orElse(null);
	}

	@Override
	public List<BindingType> getAllBindingTypes() {
		return bindingTyperepository.findAll();
	}

	@Override
	public String addBindingType(BindingTypeDTO bindingTypeDto) {
		BindingType newbindingType = new BindingType();
		newbindingType.setName(bindingTypeDto.getName());
		bindingTyperepository.save(newbindingType);
		return "binding type added";
	}

	@Override
	public void deleteById(Long id) {
		bindingTyperepository.deleteById(id);
		
	}

}
