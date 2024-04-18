package com.ppp.billing.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.ppp.billing.model.PaperGrammage;
import com.ppp.billing.model.dto.PaperGrammageDTO;
import com.ppp.billing.repository.PaperGrammageRepository;
import com.ppp.billing.service.PaperGrammageService;

public class PaperGrammageServiceImpl implements PaperGrammageService {

	@Autowired
	private PaperGrammageRepository paperGrammageRepository;

	@Override
	public PaperGrammage save(PaperGrammageDTO paperGrammageDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaperGrammage update(PaperGrammageDTO paperGrammageDTO, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PaperGrammage> findPaginatedJobType(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	} 

	

}
