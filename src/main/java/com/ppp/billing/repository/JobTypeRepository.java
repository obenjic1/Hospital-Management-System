package com.ppp.billing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ppp.billing.model.JobType;


@Repository
public interface JobTypeRepository extends JpaRepository<JobType, Long> {
	
	Optional<JobType> findByName(String name);
	Optional<JobType>  findById(long id );
	
	//@Modifying
	@Query(value ="SELECT * from job_type j ORDER BY j.category ", nativeQuery = true)
	List<JobType> listAllJobTypeOrderByCategory();
}
