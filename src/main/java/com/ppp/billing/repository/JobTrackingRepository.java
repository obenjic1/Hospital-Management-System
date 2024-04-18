package com.ppp.billing.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ppp.billing.model.JobTracking;


public interface JobTrackingRepository extends JpaRepository<JobTracking, Long> {
	
}
