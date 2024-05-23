package com.ppp.billing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.BindingType;


@Service
public interface BindingTypeService { 
	
	Optional<BindingType> findByName(String name);
	Optional<BindingType>  findById(long id);
	Page< BindingType > findPaginatedJobStatus(int pageNo, int pageSize);
	List<BindingType> listAll();
	void delete(long id);

}
