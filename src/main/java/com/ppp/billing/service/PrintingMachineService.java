package com.ppp.billing.service;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.PrintingMachine;
import com.ppp.billing.model.dto.PrintingMachineDTO;

@Service
public interface PrintingMachineService {
	
	Optional<PrintingMachine> findByAbbreviation(String abbreviation);
	PrintingMachine update (PrintingMachineDTO updatePrintingmachineDto, long id);
	Optional<PrintingMachine> findById(long id);
	PrintingMachine save (PrintingMachineDTO machineDto);
	void delete(long id);

	
	

}
