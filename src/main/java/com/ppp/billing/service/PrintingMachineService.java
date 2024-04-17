package com.ppp.billing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.PrintingMachine;
import com.ppp.billing.model.dto.PrintingMachineDTO;

@Service
public interface PrintingMachineService {
	
	PrintingMachine findByName(String name);
	String updatePrintingMachine (PrintingMachineDTO updatePrintingmachineDto, Long id);
	PrintingMachine findById(Long id);
	List<PrintingMachine> getAllPrintingMachines();
	String addPrintingMachine (PrintingMachineDTO machineDto);
	void deleteById(Long id);

	
	

}
