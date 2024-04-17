package com.ppp.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.JobActivity;

public interface JobActivityRepository extends JpaRepository<JobActivity, Long> {

}
