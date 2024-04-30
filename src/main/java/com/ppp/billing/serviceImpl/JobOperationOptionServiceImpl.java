package com.ppp.billing.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.BindingType;
import com.ppp.billing.model.JobOperationOption;
import com.ppp.billing.model.dto.JobOperationOptionDTO;
import com.ppp.billing.repository.JobOperationOptionRepository;
import com.ppp.billing.service.JobOperationOptionService;

@Service
public class JobOperationOptionServiceImpl implements JobOperationOptionService {

	@Autowired
	private JobOperationOptionRepository jobOperationOptionRepository;
	
	@Override
	public Optional<JobOperationOption> findByName(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public BindingType update(JobOperationOptionDTO jobOperationOptionDTO, long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<JobOperationOption> findById(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

//<------------------------ List with pagination --------------------->
	@Override
	public Page<JobOperationOption> findPaginatedJobOperationOption(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return jobOperationOptionRepository.findAll(pageable);
	}

	@Override
	public JobOperationOption saveJobOperationOption(JobOperationOptionDTO jobOperationOptionDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

}
