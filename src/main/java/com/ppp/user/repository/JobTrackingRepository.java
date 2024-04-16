package com.ppp.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.user.model.JobTracking;

public interface JobTrackingRepository extends JpaRepository<JobTracking, Long> {

}
