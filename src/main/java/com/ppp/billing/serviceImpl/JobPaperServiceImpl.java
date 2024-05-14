package com.ppp.billing.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.JobPaper;
import com.ppp.billing.model.dto.JobPaperDTO;
import com.ppp.billing.repository.JobPaperRepository;
import com.ppp.billing.service.JobPaperService;

@Service
public class JobPaperServiceImpl implements JobPaperService {

	@Autowired
	private JobPaperRepository jobPaperRepository;
	
	@Override
	public JobPaper save(JobPaperDTO jobPaperDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<JobPaper> findByName(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

//<---------------------- List with pagination ------------------------>	
	@Override
	public Page<JobPaper> findPaginatedJobPaper(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return jobPaperRepository.findAll(pageable);
	}
	
//<---------------------- List with pagination ------------------------>		
	@Override
	public List<JobPaper> findAll() {
		return jobPaperRepository.findAll();
	}


	@Override
	public JobPaper update(JobPaperDTO jobPaperDTO, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}


}
