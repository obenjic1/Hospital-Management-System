package com.ppp.billing.serviceImpl;

import java.util.ArrayList;
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
import com.ppp.billing.service.JobTrackingService;
import com.ppp.user.model.User;
import com.ppp.user.repository.UserRepository;

@Service
public class JobTrackingServiceImpl  {

//<---------------- Dependencies injection ---------------------->
	
	
	@Autowired
	private UserRepository userRepository;
	
//	@Override
//	public JobTracking save(JobTrackingDTO jobTrackingDTO) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	

	
	public Optional<JobTracking> findByName(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
//<---------------- List ---------------------->
	public Page<JobTracking> findPaginatedJobTracking(int pageNo, int pageSize) {
//		Pageable pageable = PageRequest.of(pageNo, pageSize);
//		return jobTrackingRepository.findAll(pageable);
		return null;
	}

	

	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	public List<JobTracking> addTracking(JobTrackingDTO jobTracking, long id) {
		return null;
		
	}


	public User findConnectedUser(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	public void findByUser() {
		
		
		
	}


	

}
