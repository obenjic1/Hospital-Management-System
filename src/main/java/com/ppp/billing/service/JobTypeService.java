package com.ppp.billing.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.JobType;
import com.ppp.billing.model.dto.JobTypeDTO;

@Service
public interface JobTypeService {
	
	JobType save(JobTypeDTO jobTypeDTO);
	Optional<JobType> findByName(String name);
	Page< JobType > findPaginatedJobType(int pageNo, int pageSize);
	JobType update(JobTypeDTO jobTypeDTO, long id);
	void delete(long id);
	
}
