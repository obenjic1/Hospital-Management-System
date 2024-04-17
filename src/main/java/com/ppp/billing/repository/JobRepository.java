package com.ppp.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.Job;


public interface JobRepository extends JpaRepository<Job, Long> {

}
