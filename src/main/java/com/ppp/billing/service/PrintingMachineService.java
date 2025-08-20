package com.ppp.billing.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.PrintingMachine;
import com.ppp.billing.model.dto.PrintingMachineDTO;

@Service
public interface PrintingMachineService {
	
	Optional<PrintingMachine> findByAbbreviation(String abbreviation);
	PrintingMachine update (PrintingMachineDTO updatePrintingmachineD, long id);
	Optional<PrintingMachine> findById(long id);
	PrintingMachine save (PrintingMachineDTO machineDto);
	List<PrintingMachine> listMachines();
	void delete(long id);
	List<PrintingMachine> findByActive(boolean status);
	List<PrintingMachine> findByIsActive();
	void  switchState(long id);


	
	

}
