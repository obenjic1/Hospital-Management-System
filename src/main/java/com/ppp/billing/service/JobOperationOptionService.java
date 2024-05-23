package com.ppp.billing.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.ppp.billing.model.JobOperationOption;

public interface JobOperationOptionService {
	
	Optional<JobOperationOption> findByName(String name);
	Optional<JobOperationOption>  findById(long id);
	Page< JobOperationOption > findPaginatedJobOperationOption(int pageNo, int pageSize);
	void delete(long id);

}
