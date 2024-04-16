package com.ppp.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.user.model.JobStatus;

public interface JobStatusRepository extends JpaRepository<JobStatus, Long> {

}
