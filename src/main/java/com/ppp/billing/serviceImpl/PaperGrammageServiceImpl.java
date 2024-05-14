package com.ppp.billing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.PaperGrammage;
import com.ppp.billing.model.dto.PaperGrammageDTO;
import com.ppp.billing.repository.PaperGrammageRepository;
import com.ppp.billing.service.PaperGrammageService;

@Service
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

//<-------------------Find all pageable -------------------->
	@Override
	public Page<PaperGrammage> findPaginatedJobType(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return paperGrammageRepository.findAll(pageable);
	}
	
//<-------------------Find all -------------------->
	@Override
	public List<PaperGrammage> findAll() {
		return paperGrammageRepository.findAll();
	} 

	


	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

}
