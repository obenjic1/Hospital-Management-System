package com.ppp.billing.serviceImpl;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ppp.billing.model.PrintingMachine;
import com.ppp.billing.model.dto.PrintingMachineDTO;
import com.ppp.billing.repository.PrintingMachineRepository;
import com.ppp.billing.service.PrintingMachineService;

@Service
public class PrintingMachineServiceImpl implements PrintingMachineService {

	@Autowired
	PrintingMachineRepository printinMachineRepository;
	
	@Override
	public Optional<PrintingMachine> findByAbbreviation(String abbreviation) {
		return printinMachineRepository.findByAbbreviation(abbreviation);
	}
	
	@Override
	public PrintingMachine update(PrintingMachineDTO machine, long id) {
		
	    PrintingMachine machineOptional = printinMachineRepository.findById(id).get();	    
	    	machineOptional.setName(machine.getName());
	    	machineOptional.setAbbreviation(machine.getAbbreviation());
	    	machineOptional.setActive(machine.isActive());
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

	@Override
	public List<PrintingMachine> listMachines() {
		return printinMachineRepository.findAll();
	}

	@Override
	public Optional<PrintingMachine> findById(long id) {	
		return printinMachineRepository.findById(id);
	}

	@Override
	public List<PrintingMachine> findByActive(boolean status) {
		return printinMachineRepository.findByActive(status);
	}

	@Override
	public List<PrintingMachine> findByIsActive() {
		return printinMachineRepository.findByActive(true);
	}
	

	@Override
	public void  switchState(long id) {	
		PrintingMachine targetMarchine = printinMachineRepository.findById(id).get();
		if(targetMarchine.isActive()) {
			targetMarchine.setActive(false);
			printinMachineRepository.save(targetMarchine);
		}
		else {
			targetMarchine.setActive(true);	
			printinMachineRepository.save(targetMarchine);
		}
}}

