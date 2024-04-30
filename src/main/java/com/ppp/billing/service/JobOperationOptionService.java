package com.ppp.billing.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.ppp.billing.model.BindingType;
import com.ppp.billing.model.JobOperationOption;
import com.ppp.billing.model.dto.JobOperationOptionDTO;

public interface JobOperationOptionService {
	
	Optional<JobOperationOption> findByName(String name);
	BindingType update (JobOperationOptionDTO jobOperationOptionDTO, long id);
	Optional<JobOperationOption>  findById(long id);
	Page< JobOperationOption > findPaginatedJobOperationOption(int pageNo, int pageSize);
	JobOperationOption saveJobOperationOption(JobOperationOptionDTO jobOperationOptionDTO);
	void delete(long id);

}
