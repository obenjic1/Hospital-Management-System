package com.ppp.billing.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.ppp.billing.model.JobTracking;


public interface JobTrackingService {
	
	Optional<JobTracking> findByName(String name);
	Page< JobTracking > findPaginatedJobTracking(int pageNo, int pageSize);
	void delete(long id);
	

}
