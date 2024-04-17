package com.ppp.billing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.PrintType;
import com.ppp.billing.model.dto.PrintTypeDTO;
@Service
public interface PrintTypeService {
	
	PrintType findByName(String name);
	String updatePrintType (PrintTypeDTO printTypeDto, Long id);
	PrintType findById(Long id);
	List<PrintType> getAllPrintTypes();
	String addPrintType (PrintTypeDTO machineDto);
	void deleteById(Long id);
}
