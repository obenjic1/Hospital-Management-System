package com.ppp.billing.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.JobTracking;
import com.ppp.billing.repository.JobTrackingRepository;
import com.ppp.billing.service.JobTrackingService;

@Service
public class JobTrackingServiceImpl implements JobTrackingService {

//<---------------- Dependencies injection ---------------------->
	@Autowired
	private JobTrackingRepository jobTrackingRepository;
	
//	@Override
//	public JobTracking save(JobTrackingDTO jobTrackingDTO) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	

	
	@Override
	public Optional<JobTracking> findByName(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
//<---------------- List ---------------------->
	@Override
	public Page<JobTracking> findPaginatedJobTracking(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return jobTrackingRepository.findAll(pageable);
	}

//	@Override
//	public JobTracking update(JobTypeDTO jobTrackingDTO, long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

}
