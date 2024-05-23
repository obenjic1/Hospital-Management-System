package com.ppp.billing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.JobType;

@Service
public interface JobTypeService {
	
	Optional<JobType> findByName(String name);
	List<JobType> findAll();
	Page< JobType > findPaginatedJobType(int pageNo, int pageSize);
	void delete(long id);
	
}
