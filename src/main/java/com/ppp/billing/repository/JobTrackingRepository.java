package com.ppp.billing.repository;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppp.billing.model.Job;
import com.ppp.billing.model.JobTracking;
import com.ppp.billing.model.dto.JobDTO;



public interface JobTrackingRepository extends JpaRepository<JobTracking, Long> {
	
	JobTracking findByOperation(String operation);
//	@Modifying
	@Query(value ="select  j.*  FROM  job j JOIN job_tracking jt ON j.id=jt.job_id JOIN user u ON jt.user_id = u.id where u.username = :username ; ", nativeQuery=true)
	List <JobDTO> findJobsByUser(@Param("username") String userId);
}
