package com.ppp.billing.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.JobTracking;
import com.ppp.billing.model.dto.JobTrackingDTO;
import com.ppp.billing.repository.JobRepository;
import com.ppp.billing.repository.JobTrackingRepository;
import com.ppp.billing.service.JobTrackingService;
import com.ppp.user.model.User;
import com.ppp.user.repository.UserRepository;

@Service
public class JobTrackingServiceImpl implements JobTrackingService {

//<---------------- Dependencies injection ---------------------->
	@Autowired
	private JobTrackingRepository jobTrackingRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private UserRepository userRepository;
	
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
//		Pageable pageable = PageRequest.of(pageNo, pageSize);
//		return jobTrackingRepository.findAll(pageable);
		return null;
	}

	@Override
	public JobTracking save(JobTrackingDTO jobTrackingDTO, long id) {
		JobTracking jobTracking = new JobTracking();
		jobTracking.setCreationDate(new Date());
		jobTracking .setOperation(jobTrackingDTO.getOperation());
		jobTracking.setJob(jobRepository.findById(id).get());
		jobTracking.setId(id);
		
		
		return jobTrackingRepository.save(jobTracking);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<JobTracking> addTracking(JobTrackingDTO jobTracking, long id) {
		List<JobTracking> jobTrackings = jobRepository.findById(id).get().getJobTrackings();
		JobTracking tracking = new JobTracking();
		tracking.setCreationDate(new Date());
		tracking.setOperation(jobTracking.getOperation());
		tracking.setJob(jobRepository.findById(id).get());
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(name);
		System.out.println(user.getEmail());
		tracking.setUser(user);
		jobTrackings.add(tracking);
		return jobTrackingRepository.saveAll(jobTrackings);
	}

}
