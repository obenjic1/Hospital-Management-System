package com.ppp.billing.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.ppp.billing.model.JobActivity;
import com.ppp.billing.model.dto.JobActivityDTO;

public interface JobActivityService {
	
	JobActivity save(JobActivityDTO jobActivityDTO);
	Optional<JobActivity> findByName(String name);
	Page< JobActivity > findPaginatedJobActivity(int pageNo, int pageSize);
	JobActivity update(JobActivityDTO jobActivityDTO, long id);
	void delete(long id);

}
