package com.ppp.billing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.BindingType;


@Service
public interface BindingTypeService { 
	
	Optional<BindingType> findByName(String name);
	BindingType  findById(int id);
	Page< BindingType > findPaginatedJobStatus(int pageNo, int pageSize);
	List<BindingType> listAll();
	BindingType update(BindingType bindingType, int id);
	void delete(long id);

}
