package com.ppp.billing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ppp.billing.model.JobActivityOption;


public interface JobActivityOptionRepository extends JpaRepository<JobActivityOption,Long>{

	Optional<JobActivityOption>  findById(long id );
	Optional<JobActivityOption> findByName(String name);
}
