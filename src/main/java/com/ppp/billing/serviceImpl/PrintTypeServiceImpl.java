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
	public PrintType findByName(String name) {
		return printTyperepository.findByName(name);
	}
	
	@Override
	public String addPrintType(PrintTypeDTO printTypeDto) {
		PrintType newPrintType = new PrintType();
		newPrintType.setName(printTypeDto.getName());
		printTyperepository.save(newPrintType);
		return "new printtype added";
	}


	@Override
	public String updatePrintType(PrintTypeDTO printTypeDto, Long id) {
		Optional<PrintType> printType = printTyperepository.findById(id);
        if (PrintType.isPresent()) {
        	PrintType updatePrintType = PrintType.get();
        	updatePrintType.setName(printTypeDto.getName());
        	printTyperepository.save(updatePrintType);
        	return "PrintType updated";
        	}
        return "error";
	}

	@Override
	public PrintType findById(Long id) {
		Optional<PrintType> printType = printTyperepository.findById(id);
        return printType.orElse(null);	
	}
	
	@Override
	public List<PrintType> getAllPrintTypes() {
		return printTyperepository.findAll() ;
	}

	@Override
	public void deleteById(Long id) {
		printTyperepository.deleteById(id);
	}

	
}
