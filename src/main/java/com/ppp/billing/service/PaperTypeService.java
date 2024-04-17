package com.ppp.billing.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.PaperType;
import com.ppp.billing.model.dto.PaperTypeDTO;

@Service
public interface PaperTypeService {
	
		String addPaperType(PaperTypeDTO paperTypeDTO);
		String updateJobType(PaperTypeDTO paperTypeDTO, Long id);
		PaperType findByName(String name);
		Page< PaperType > findPaginatedJobType(int pageNo, int pageSize);
		void delatePaperType(Long id);

}
