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
	public Optional<BindingType>  findByName(String name) {
		return bindingTyperepository.findByName(name);
	}

	@Override
	public BindingType update(BindingTypeDTO updatbindingTypeDto, long id) {
		Optional<BindingType> bindingType = bindingTyperepository.findById(id);
        if (bindingType.isPresent()) {
        	BindingType updatedBindingType = bindingType.get();
        	updatedBindingType.setName(updatbindingTypeDto.getName());
        	return bindingTyperepository.save(updatedBindingType);
		} return null;
			
     
	}

	@Override
	public  Optional<BindingType> findById(long id) {
		return bindingTyperepository.findById(id);
	}

	@Override
	public List<BindingType> findAll() {
		return bindingTyperepository.findAll();
	}

	@Override
	public BindingType save (BindingTypeDTO bindingTypeDto) {
		BindingType newbindingType = new BindingType();
		newbindingType.setName(bindingTypeDto.getName());
		return bindingTyperepository.save(newbindingType);
		 
	}

	@Override
	public void delete(long id) {
		bindingTyperepository.deleteById(id);
		
	}

}
