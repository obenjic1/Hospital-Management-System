package com.ppp.billing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ppp.billing.model.JobStatus;


public interface JobStatusRepository extends JpaRepository<JobStatus, Long> {
	
	Optional<JobStatus>  findById(long Id );
}
