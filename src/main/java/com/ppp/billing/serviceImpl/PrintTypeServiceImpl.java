package com.ppp.billing.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.PrintType;
import com.ppp.billing.repository.PrintTypeRepository;
import com.ppp.billing.service.PrintTypeService;


@Service
public class PrintTypeServiceImpl implements PrintTypeService{
	
	@Autowired
	PrintTypeRepository printTyperepository;

	@Override
	public Optional<PrintType> findByName(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

//	@Override
//	public PrintType update(PrintTypeDTO printTypeDto, long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Optional<PrintType> findById(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
//<-------------------------------- List all with pagination--------------------------------->	
	@Override
	public Page<PrintType> paginatedList(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return printTyperepository.findAll(pageable);
	}
	
//<-------------------------------- List all --------------------------------->	
	@Override
	public List<PrintType> findAll() {
		// TODO Auto-generated method stub
		return printTyperepository.findAll();
	}


//	
//	@Override
//	public PrintType save(PrintTypeDTO machine) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}


	
}
