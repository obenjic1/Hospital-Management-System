package com.ppp.billing.repository;

import com.ppp.billing.model.JobEstimate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface JobEstimateRepository extends JpaRepository<JobEstimate, Long> {


}
