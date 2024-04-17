package com.ppp.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.JobType;


public interface JobTypeRepository extends JpaRepository<JobType, Long> {

}
