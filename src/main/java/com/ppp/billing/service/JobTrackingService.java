package com.ppp.billing.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.ppp.billing.model.JobTracking;
import com.ppp.billing.model.dto.JobTrackingDTO;
import com.ppp.billing.model.dto.JobTypeDTO;

public interface JobTrackingService {
	
	JobTracking save(JobTrackingDTO jobTrackingDTO);
	Optional<JobTracking> findByName(String name);
	Page< JobTracking > findPaginatedJobTracking(int pageNo, int pageSize);
	JobTracking update(JobTypeDTO jobTrackingDTO, long id);
	void delete(long id);
	

}
