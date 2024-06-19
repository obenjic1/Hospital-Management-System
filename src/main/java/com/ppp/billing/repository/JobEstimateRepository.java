package com.ppp.billing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.JobEstimate;


public interface JobEstimateRepository extends JpaRepository<JobEstimate, Long> {
	Optional<JobEstimate> findByReference(String reference);
	
	


}
