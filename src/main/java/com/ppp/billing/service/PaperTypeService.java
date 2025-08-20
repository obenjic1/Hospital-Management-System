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
		PaperType update(PaperTypeDTO paperTypeDTO, int id);
		Optional<PaperType> findByName(String name);
		PaperType findById(int id);
		PaperType findToUpdate(int id);
		void delete(int id);		
		Page<PaperType> paginatedList(int pageNo, int pageSize);
		List<PaperType> listAll();

}
