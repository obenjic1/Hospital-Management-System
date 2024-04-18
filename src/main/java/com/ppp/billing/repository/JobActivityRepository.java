package com.ppp.billing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ppp.billing.model.JobActivity;


public interface JobActivityRepository extends JpaRepository<JobActivity, Long> {

	Optional<JobActivity>  findById(long id );
}
