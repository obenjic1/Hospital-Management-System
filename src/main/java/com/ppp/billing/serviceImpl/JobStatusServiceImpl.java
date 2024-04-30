package com.ppp.billing.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.JobStatus;
import com.ppp.billing.model.dto.JobStatusDTO;
import com.ppp.billing.model.dto.JobTypeDTO;
import com.ppp.billing.repository.JobStatusRepository;
import com.ppp.billing.service.JobStatusService;

@Service
public class JobStatusServiceImpl implements JobStatusService {
	
	@Autowired
	public JobStatusRepository jobStatusRepository;

	@Override
	public JobStatus save(JobStatusDTO jobStatusDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<JobStatus> findByName(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

//<--------------------- List with pagination ------------------------->
	@Override
	public Page<JobStatus> findPaginatedJobStatus(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return jobStatusRepository.findAll(pageable);
	}

	@Override
	public JobStatus update(JobTypeDTO jobTrackingDTO, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

}
