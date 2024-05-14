package com.ppp.billing.service;

import java.util.List;
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
		PaperType findById(Long id);
		PaperType findToUpdate(Long id);
		void delete(long id);		
		Page<PaperType> paginatedList(int pageNo, int pageSize);
		List<PaperType> listAll();

}
