package com.ppp.billing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ppp.billing.model.PrintType;
import com.ppp.billing.model.PrintingMachine;
public interface PrintTypeRepository extends JpaRepository<PrintType,Long>  {

	Optional<PrintType> findByName(String name);
	Optional<PrintType> findById(long id);

}
