package com.ppp.billing.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.PaperType;
import com.ppp.billing.model.dto.PaperTypeDTO;
import com.ppp.billing.repository.PaperTypeRepository;
import com.ppp.billing.service.PaperTypeService;

@Service
public class PaperTypeServiceImpl implements PaperTypeService {

	@Autowired
	private PaperTypeRepository paperTypeRepository;

//<-----------------  Save paper type --------------------->	
	@Override
	public PaperType save(PaperTypeDTO paperTypeDTO) {
		PaperType paperType = new PaperType();
		paperType.setName(paperTypeDTO.getName());
		return paperTypeRepository.save(paperType);
	}
	


//<-----------------  Find to update --------------------->
	@Override
	public PaperType findToUpdate(Long id) {		
		return paperTypeRepository.findById(id).get();
	}

//<-----------------  Update paper type --------------------->	
	@Override
	public PaperType update(PaperTypeDTO paperTypeDTO, long id) {
		PaperType paperType = paperTypeRepository.findById(id).get();
			paperType.setName(paperTypeDTO.getName());	
			paperTypeRepository.save(paperType);
		return null;
	}

//<----------------- Find by name --------------------->	
	@Override
	public Optional<PaperType> findByName(String name) {
		Optional<PaperType> result = paperTypeRepository.findByName(name); 
		return result;
	}

//<-----------------  papers types --------------------->
	@Override
	public Page<PaperType> paginatedList(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo -1, pageSize);
		return paperTypeRepository.findAll(pageable);
	}

//<----------------- Find by id --------------------->
	@Override
	public PaperType findById(Long id) {
		return paperTypeRepository.findById(id).get();
	}

//<-----------------  Delete with id --------------------->
	@Override
	public void delete(long id) {
		PaperType paperType = paperTypeRepository.findById(id).get();
		paperTypeRepository.delete(paperType);
		
	}



	@Override
	public List<PaperType> listAll() {
		return paperTypeRepository.findAll();
	}


}
