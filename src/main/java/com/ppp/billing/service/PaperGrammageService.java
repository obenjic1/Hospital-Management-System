package com.ppp.billing.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.PaperGrammage;
import com.ppp.billing.model.dto.PaperGrammageDTO;

@Service
public interface PaperGrammageService {
	
	String addPaperGrammage(PaperGrammageDTO paperGrammageDTO);
	PaperGrammage findByValue(String name);
	String updatePaperGrammage(PaperGrammageDTO paperGrammageDTO, Long id);
	Page< PaperGrammage > findPaginatedJobType(int pageNo, int pageSize);
	void deletePaperGrammage(Long id);

}
