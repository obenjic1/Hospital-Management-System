package com.ppp.billing.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.PaperFormat;
import com.ppp.billing.model.dto.PaperGrammageDTO;

@Service
public interface PaperFormatService {

	String addPaperFormat(PaperGrammageDTO paperGrammageDTO);
	PaperFormat findByName(String name);
	PaperFormat updatePaperFormat(PaperGrammageDTO paperGrammageDTO, Long id);
	Page< PaperFormat > getAllWithPagination(int pageNum, int pageSize);
	void deletePaperFormat(Long id);
}
