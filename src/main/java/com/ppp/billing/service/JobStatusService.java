package com.ppp.billing.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.ppp.billing.model.JobStatus;

public interface JobStatusService {
	
	Optional<JobStatus> findByName(String name);
	Page< JobStatus > findPaginatedJobStatus(int pageNo, int pageSize);
	JobStatus findById(long id);
	void delete(long id);
	JobStatus save(JobStatus jobStatus);
	JobStatus update(long idJob,long idStatus);

}
