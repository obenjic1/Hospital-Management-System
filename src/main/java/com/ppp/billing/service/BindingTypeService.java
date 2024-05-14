package com.ppp.billing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.BindingType;
import com.ppp.billing.model.dto.BindingTypeDTO;


@Service
public interface BindingTypeService { 
	
	Optional<BindingType> findByName(String name);
	BindingType update (BindingTypeDTO bindingTypeDTO, long id);
	Optional<BindingType>  findById(long id);
	Page< BindingType > findPaginatedJobStatus(int pageNo, int pageSize);
	List<BindingType> listAll();
	BindingType saveBindingType(BindingTypeDTO bindingTypeDTO);
	void delete(long id);

}
