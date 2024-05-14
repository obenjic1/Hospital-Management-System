package com.ppp.billing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.JobColorCombination;
import com.ppp.billing.model.dto.JobColorCombinationDTO;
import com.ppp.billing.repository.JobColorCombinationRepository;
import com.ppp.billing.service.JobColorCombinationService;

@Service
public class JobColorCombinationServiceImpl implements JobColorCombinationService {

	@Autowired
	private JobColorCombinationRepository jobColorCombinationRepository;
	
	@Override
	public JobColorCombination save(JobColorCombinationDTO cutomerDTO) {
		// TODO Auto-generated method stub
		return null;
	}

//<-------------------- List all with pagination --------------------->	
	@Override
	public Page<JobColorCombination> pagination(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return jobColorCombinationRepository.findAll(pageable);
	}
	
//<-------------------- List all --------------------->		
	@Override
	public List<JobColorCombination> findAll() {
		return jobColorCombinationRepository.findAll();
	}
	
	

	@Override
	public JobColorCombination findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JobColorCombination update(JobColorCombinationDTO customerDTO, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}


}
