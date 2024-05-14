package com.ppp.billing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.ppp.billing.model.Job;
import com.ppp.billing.model.dto.JobColorCombinationDTO;
import com.ppp.billing.model.dto.JobDTO;
import com.ppp.billing.model.dto.JobPaperDTO;

public interface JobService {
	

	Optional<Job> findByTitle(String name);
	Job update (JobDTO jobDTO, long id);
	Optional< Job >  findById(long id);
	Page< Job > findPaginatedJob(int pageNo, int pageSize);
	Job saveJob(JobDTO jobDTO, JobPaperDTO jobPaperDTO, JobColorCombinationDTO jobColorCombinationDTO);
	void delete(long id);
	List<Job> findall();
	


}
