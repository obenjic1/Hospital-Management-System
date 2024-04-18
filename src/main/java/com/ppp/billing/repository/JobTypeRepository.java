package com.ppp.billing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ppp.billing.model.JobType;


@Repository
public interface JobTypeRepository extends JpaRepository<JobType, Long> {
	
	Optional<JobType> findByName(String name);
	Optional<JobType>  findById(long Id );

}
