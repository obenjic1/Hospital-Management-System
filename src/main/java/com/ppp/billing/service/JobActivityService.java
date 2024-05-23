package com.ppp.billing.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.ppp.billing.model.JobActivity;

public interface JobActivityService {
	
	Optional<JobActivity> findByName(String name);
	Page< JobActivity > findPaginatedJobActivity(int pageNo, int pageSize);
	void delete(long id);

}
