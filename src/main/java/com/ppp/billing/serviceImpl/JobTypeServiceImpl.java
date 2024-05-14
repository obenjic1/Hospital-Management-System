package com.ppp.billing.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.JobType;
import com.ppp.billing.model.dto.JobTypeDTO;
import com.ppp.billing.repository.JobTypeRepository;
import com.ppp.billing.service.JobTypeService;

@Service
public class JobTypeServiceImpl implements JobTypeService {
	
	@Autowired
	private JobTypeRepository jobTypeRepository;

	@Override
	public JobType save(JobTypeDTO jobTypeDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<JobType> findByName(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
//<---------------- List with pagination ---------------------->	
	@Override
	public Page<JobType> findPaginatedJobType(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return jobTypeRepository.findAll(pageable);
	}
	
//<---------------- List ---------------------->		
	@Override
	public List<JobType> findAll() {
		return jobTypeRepository.findAll();
	}
	

	@Override
	public JobType update(JobTypeDTO jobTypeDTO, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}


	
}
