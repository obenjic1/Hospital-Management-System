package com.ppp.billing.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.ppp.billing.model.PrintType;
import com.ppp.billing.model.dto.PrintTypeDTO;
import com.ppp.billing.repository.PrintTypeRepository;
import com.ppp.billing.service.PrintTypeService;

public class PrintTypeServiceImpl implements PrintTypeService{
	
	@Autowired
	PrintTypeRepository printTyperepository;

	@Override
	public Optional<PrintType> findByName(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public PrintType update(PrintTypeDTO printTypeDto, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PrintType> findById(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<PrintType> findAllPrintTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintType save(PrintTypeDTO machine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	
	
}
