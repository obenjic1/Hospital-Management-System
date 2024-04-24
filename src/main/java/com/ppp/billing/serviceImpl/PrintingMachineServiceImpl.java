package com.ppp.billing.serviceImpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.Customer;
import com.ppp.billing.model.PrintingMachine;
import com.ppp.billing.model.dto.PrintingMachineDTO;
import com.ppp.billing.repository.PrintingMachineRepository;
import com.ppp.billing.service.PrintingMachineService;
import com.ppp.user.model.User;
import com.ppp.user.model.dto.UserDTO;
import com.ppp.user.service.FileStorageService;

@Service
public class PrintingMachineServiceImpl implements PrintingMachineService {

	@Autowired
	PrintingMachineRepository printinMachineRepository;
	
	

	@Override
	public Optional<PrintingMachine> findByAbbreviation(String abbreviation) {
		return printinMachineRepository.findByAbbreviation(abbreviation);
	}
	
	@Override
	public PrintingMachine update(PrintingMachine machine, long id) {
		
	    PrintingMachine machineOptional = printinMachineRepository.findById(id).get();	    
	    	machineOptional.setName(machine.getName());
	    	machineOptional.setAbbreviation(machine.getAbbreviation());
	    
	    	machineOptional.setPlateLength(machine.getPlateLength());
	    	machineOptional.setPlateWidth(machine.getPlateWidth());
	    	machineOptional.setThumbnail(machine.getThumbnail());
	    	printinMachineRepository.save(machineOptional);
	       return machineOptional;
	}

	@Override
	public PrintingMachine save(PrintingMachineDTO machineDto) {
		PrintingMachine newMachine = new PrintingMachine();
		newMachine.setName(machineDto.getName());
		newMachine.setAbbreviation(machineDto.getAbbreviation());
		newMachine.setActive(machineDto.isActive());
		newMachine.setPlateLength(machineDto.getPlateLength());
		newMachine.setPlateWidth(machineDto.getPlateWidth());
		newMachine.setThumbnail(machineDto.getThumbnail());
		newMachine.setCreationDate(new Date());
		return printinMachineRepository.save(newMachine);
	}

	@Override
	public void delete(long id) {
		printinMachineRepository.deleteById(id);
	}

	public List<PrintingMachine> listMachines() {
		return printinMachineRepository.findAll();
	}

	@Override
	public Optional<PrintingMachine> findById(long id) {	
		return printinMachineRepository.findById(id);
	}

	
	
	


}
