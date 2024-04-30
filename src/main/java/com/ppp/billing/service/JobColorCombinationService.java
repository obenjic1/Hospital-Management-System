package com.ppp.billing.service;

import org.springframework.data.domain.Page;

import com.ppp.billing.model.JobColorCombination;
import com.ppp.billing.model.dto.JobColorCombinationDTO;

public interface JobColorCombinationService {
	
	JobColorCombination save(JobColorCombinationDTO cutomerDTO);
	Page<JobColorCombination> pagination(int pageNo, int pageSize);
	JobColorCombination findById(Long id);
	JobColorCombination update(JobColorCombinationDTO customerDTO, Long id);
	void delete(long id);


}
