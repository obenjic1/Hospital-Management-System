package com.ppp.billing.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.PaperGrammage;
import com.ppp.billing.model.dto.PaperGrammageDTO;

@Service
public interface PaperGrammageService {
	
	PaperGrammage save(PaperGrammageDTO paperGrammageDTO);
	PaperGrammage update(PaperGrammageDTO paperGrammageDTO, long id);
	Page <PaperGrammage> findPaginatedJobType(int pageNo, int pageSize);
	List<PaperGrammage> findAll();
	void delete(long id);

}
