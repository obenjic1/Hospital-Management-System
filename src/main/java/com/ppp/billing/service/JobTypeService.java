package com.ppp.billing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.JobType;
import com.ppp.billing.model.dto.JobTypeDTO;

@Service
public interface JobTypeService {
	
	Optional<JobType> findByName(String name);
	List<JobType> findAll();
	JobType save(JobTypeDTO jobTypeDto);
	JobType update(JobTypeDTO jobTypeDto);
	Page< JobType > findPaginatedJobType(int pageNo, int pageSize);
	void delete(long id);
	List<JobType> listAllJobTypeOrderByCategory();
	
}
