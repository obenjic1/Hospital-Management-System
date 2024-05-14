package com.ppp.billing.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.BindingType;
import com.ppp.billing.model.dto.BindingTypeDTO;
import com.ppp.billing.repository.BindingTypeRepository;
import com.ppp.billing.service.BindingTypeService;

@Service
public class BindingTypeserviceImpl implements BindingTypeService {

	@Autowired
	BindingTypeRepository bindingTyperepository;

	@Override
	public Optional<BindingType> findByName(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public BindingType update(BindingTypeDTO bindingTypeDTO, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BindingType> findById(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

//<------------------- List with pagination -------------------->	
	@Override
	public Page<BindingType> findPaginatedJobStatus(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return bindingTyperepository.findAll(pageable);
	}
	
//<------------------- List with pagination -------------------->	
	@Override
	public List<BindingType> listAll() {
		return bindingTyperepository.findAll();
	}


	@Override
	public BindingType saveBindingType(BindingTypeDTO bindingTypeDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}


}
