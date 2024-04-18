package com.ppp.billing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ppp.billing.model.JobColorCombination;


public interface JobColorCombinationRepository extends JpaRepository<JobColorCombination,Long>{

	Optional<JobColorCombination>  findById(long Id );
}
