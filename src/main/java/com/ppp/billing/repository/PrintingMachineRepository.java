package com.ppp.billing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.PrintingMachine;
import com.ppp.billing.model.dto.PrintingMachineDTO;


public interface PrintingMachineRepository extends JpaRepository<PrintingMachine,Long> {

	Optional<PrintingMachine> findByAbbreviation(String abbreviation);
	Optional<PrintingMachine> findById(long id);
	PrintingMachine save(PrintingMachineDTO newMachine);

    List<PrintingMachine> findByActive(boolean status);
}
