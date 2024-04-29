package com.ppp.billing.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.ppp.billing.model.JobStatus;
import com.ppp.billing.model.dto.JobStatusDTO;
import com.ppp.billing.model.dto.JobTypeDTO;

public interface JobStatusService {
	
	JobStatus save(JobStatusDTO jobStatusDTO);
	Optional<JobStatus> findByName(String name);
	Page< JobStatus > findPaginatedJobStatus(int pageNo, int pageSize);
	JobStatus update(JobTypeDTO jobTrackingDTO, long id);
	void delete(long id);

}
