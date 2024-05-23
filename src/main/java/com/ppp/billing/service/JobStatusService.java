package com.ppp.billing.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.ppp.billing.model.JobStatus;

public interface JobStatusService {
	
	Optional<JobStatus> findByName(String name);
	Page< JobStatus > findPaginatedJobStatus(int pageNo, int pageSize);
	void delete(long id);

}
