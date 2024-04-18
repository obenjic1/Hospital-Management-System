package com.ppp.billing.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ppp.billing.model.PrintingMachine;
import com.ppp.billing.model.dto.PrintingMachineDTO;
import com.ppp.billing.repository.PrintingMachineRepository;
import com.ppp.billing.service.PrintingMachineService;
import com.ppp.user.service.FileStorageService;

@Service
public class PrintingMachineServiceImpl implements PrintingMachineService {

	@Autowired
	PrintingMachineRepository printinMachineRepository;
	
	

	@Override
	public Optional<PrintingMachine> findByAbbreviation(String abbreviation) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public PrintingMachine update(PrintingMachineDTO updatePrintingmachineDto, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PrintingMachine> findById(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public PrintingMachine save(PrintingMachineDTO machineDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}
	
	


}
