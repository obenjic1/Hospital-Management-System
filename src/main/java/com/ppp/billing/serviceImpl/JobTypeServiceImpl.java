package com.ppp.billing.serviceImpl;

import org.springframework.data.domain.Page;

import com.ppp.billing.model.JobType;
import com.ppp.billing.model.dto.JobTypeDTO;
import com.ppp.billing.service.JobTypeService;


public class JobTypeServiceImpl implements JobTypeService {

	@Override
	public String addJobType(JobTypeDTO jobTypeDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobType findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<JobType> findPaginatedJobType(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteJobType(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String updateJobType(JobTypeDTO jobTypeDTO, Long id) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
