package com.ppp.billing.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.PrintType;
import com.ppp.billing.model.dto.PrintTypeDTO;
@Service
public interface PrintTypeService {
	
	Optional<PrintType> findByName(String name);
	PrintType update (PrintTypeDTO printTypeDto, long id);
	Optional<PrintType> findById(long id);
	Page<PrintType> paginatedList(int pageNo, int pageSize);
	PrintType save (PrintTypeDTO machine);
	void delete(long id);
	
	
}
