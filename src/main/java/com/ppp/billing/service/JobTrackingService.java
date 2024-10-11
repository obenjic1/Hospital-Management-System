package com.ppp.billing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import com.ppp.billing.model.Job;
import com.ppp.billing.model.JobTracking;
import com.ppp.billing.model.dto.JobTrackingDTO;
import com.ppp.user.model.User;


public interface JobTrackingService {
	
	Optional<JobTracking> findByName(String name);
	Page< JobTracking > findPaginatedJobTracking(int pageNo, int pageSize);
	void delete(long id);
	JobTracking save(JobTrackingDTO jobTrackingDTO, long id);
	List<JobTracking> addTracking (JobTrackingDTO jobTracking,long id);
	User findConnectedUser (String username);
	List<Job> findByUser();
	

}
