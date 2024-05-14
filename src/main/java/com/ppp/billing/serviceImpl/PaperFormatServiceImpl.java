package com.ppp.billing.serviceImpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.PaperFormat;
import com.ppp.billing.model.dto.PaperFormatDTO;
import com.ppp.billing.repository.PaperFormatRepository;
import com.ppp.billing.service.PaperFormatService;

@Service
public class PaperFormatServiceImpl implements PaperFormatService {

	@Autowired
	private PaperFormatRepository paperFormatRepository;

	@Override
	public PaperFormat save(PaperFormatDTO paperFormatDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PaperFormat> findByName(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public PaperFormat update(PaperFormatDTO paperFormatDTO, long id) {
		// TODO Auto-generated method stub
		return null;
	}

//<---------------- List all with pagination ---------------------->
	@Override
	public Page<PaperFormat> findAllWithPagination(int pageNum, int pageSize) {
		Pageable pageable = PageRequest.of(pageNum, pageSize);
		return paperFormatRepository.findAll(pageable);
	}

//<---------------- List all ---------------------->
	@Override
	public List<PaperFormat> findAll() {
		return paperFormatRepository.findAll();
	}


	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	
}
