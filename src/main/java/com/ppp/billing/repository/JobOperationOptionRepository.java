package com.ppp.billing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ppp.billing.model.JobOperationOption;


public interface JobOperationOptionRepository extends JpaRepository<JobOperationOption,Long>{
	
	Optional<JobOperationOption>  findById(long Id );
}
