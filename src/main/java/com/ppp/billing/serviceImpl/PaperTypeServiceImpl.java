package com.ppp.billing.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.ppp.billing.model.PaperType;
import com.ppp.billing.model.dto.PaperTypeDTO;
import com.ppp.billing.repository.PaperTypeRepository;
import com.ppp.billing.service.PaperTypeService;


public class PaperTypeServiceImpl implements PaperTypeService {

	@Autowired
	private PaperTypeRepository paperTypeRepository;

	@Override
	public PaperType save(PaperTypeDTO paperTypeDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaperType update(PaperTypeDTO paperTypeDTO, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PaperType> findByName(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Page<PaperType> findPaginatedJobType(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	

}
