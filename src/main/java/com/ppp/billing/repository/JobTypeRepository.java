package com.ppp.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppp.billing.model.JobType;


@Repository
public interface JobTypeRepository extends JpaRepository<JobType, Long> {
	
	JobType findByName(String name);

}
