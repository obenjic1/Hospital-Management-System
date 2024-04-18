package com.ppp.billing.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.PaperFormat;
import com.ppp.billing.model.dto.PaperFormatDTO;
import com.ppp.billing.model.dto.PaperGrammageDTO;

@Service
public interface PaperFormatService {

	PaperFormat save(PaperFormatDTO paperFormatDTO);
	Optional<PaperFormat> findByName(String name);
	PaperFormat update(PaperFormatDTO paperFormatDTO, long id);
	Page <PaperFormat> findAllWithPagination(int pageNum, int pageSize);
	void delete(long id);
}
