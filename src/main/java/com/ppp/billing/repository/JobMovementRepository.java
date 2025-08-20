package com.ppp.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppp.billing.model.JobMovement;

@Repository
public interface JobMovementRepository extends JpaRepository<JobMovement,Long> {
	
	

}
