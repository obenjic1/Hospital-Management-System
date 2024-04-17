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
	private FileStorageService fileStorageService;
	
	@Override
	public PrintingMachine findByName(String name) {
		return printinMachineRepository.findByName(name);
	}

	@Override
	public String updatePrintingMachine(PrintingMachineDTO updatePrintingmachineDto, Long id) {
		Optional<PrintingMachine> printMachine = printinMachineRepository.findById(id);
        if (printMachine.isPresent()) {
        	PrintingMachine updatedPrintMachine = printMachine.get();
        	updatedPrintMachine.setName(updatePrintingmachineDto.getName());
        	updatedPrintMachine.setPlateLength(updatePrintingmachineDto.getPlateLength());
        	updatedPrintMachine.setPlateWidth(updatePrintingmachineDto.getPlateWidth());
        	updatedPrintMachine.setCreation_date(updatePrintingmachineDto.getCreation_date());
        	updatedPrintMachine.set_active(updatePrintingmachineDto.is_active());
        	printinMachineRepository.save(updatedPrintMachine);
        	return "machine updated";
        	}
        return " failed to update machine ";
	}

	@Override
	public PrintingMachine findById(Long id) {
		Optional<PrintingMachine> printMachine = printinMachineRepository.findById(id);
        return printMachine.orElse(null);
	}

	@Override
	public List<PrintingMachine> getAllPrintingMachines() {
		return printinMachineRepository.findAll() ;
	}

	@Override
	public String addPrintingMachine(PrintingMachineDTO machineDto) {
		PrintingMachine newPriningMachine = new PrintingMachine();
		newPriningMachine.setName(machineDto.getName());
		newPriningMachine.setPlateLength(machineDto.getPlateLength());
		newPriningMachine.setPlateWidth(machineDto.getPlateWidth());
		newPriningMachine.setCreation_date(machineDto.getCreation_date());
		newPriningMachine.set_active(machineDto.is_active());
		if (machineDto.getImageFile() != null && !machineDto.getImageFile().isEmpty()) {
            try {
                String imagePath = fileStorageService.storeFile(machineDto.getImageFile());
                newPriningMachine.setThumbnail(imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
		printinMachineRepository.save(newPriningMachine);
		return "machine added" ;
	}
		return "machine not saved";
				}

	@Override
	public void deleteById(Long id) {
		printinMachineRepository.deleteById(id);
		
	}

	


}
