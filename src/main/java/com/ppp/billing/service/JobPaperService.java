package com.ppp.billing.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.ppp.billing.model.JobPaper;
import com.ppp.billing.model.dto.JobPaperDTO;

public interface JobPaperService {
	
	JobPaper save(JobPaperDTO jobPaperDTO);
	Optional<JobPaper> findByName(String name);
	Page< JobPaper > findPaginatedJobPaper(int pageNo, int pageSize);
	JobPaper update(JobPaperDTO jobPaperDTO, long id);
	void delete(long id);


}
