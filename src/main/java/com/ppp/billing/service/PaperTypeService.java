package com.ppp.billing.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.PaperType;
import com.ppp.billing.model.dto.PaperTypeDTO;

@Service
public interface PaperTypeService {
	
		PaperType save(PaperTypeDTO paperTypeDTO);
		PaperType update(PaperTypeDTO paperTypeDTO, long id);
		Optional<PaperType> findByName(String name);
		Page <PaperType> findPaginatedJobType(int pageNo, int pageSize);
		void delete(long id);

}
