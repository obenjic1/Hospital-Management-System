package com.ppp.billing.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.JobActivity;
import com.ppp.billing.model.dto.JobActivityDTO;
import com.ppp.billing.repository.JobActivityRepository;
import com.ppp.billing.service.JobActivityService;

@Service
public class JobActivityServiceImpl implements JobActivityService {

	@Autowired
	private JobActivityRepository jobActivityRepository;
	
	@Override
	public JobActivity save(JobActivityDTO jobActivityDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<JobActivity> findByName(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

//<------------------- List with pagination -------------------->		
	@Override
	public Page<JobActivity> findPaginatedJobActivity(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return jobActivityRepository.findAll(pageable);
	}

	@Override
	public JobActivity update(JobActivityDTO jobActivityDTO, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

}
