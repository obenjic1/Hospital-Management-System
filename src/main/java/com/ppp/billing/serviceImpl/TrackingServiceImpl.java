package com.ppp.billing.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.Tracking;
import com.ppp.billing.model.dto.JobTrackingDTO;
import com.ppp.user.model.User;
import com.ppp.user.repository.UserRepository;

@Service
public class TrackingServiceImpl  {

//<---------------- Dependencies injection ---------------------->
	
	
	@Autowired
	private UserRepository userRepository;
	
//	@Override
//	public JobTracking save(JobTrackingDTO jobTrackingDTO) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	

	
	public Optional<Tracking> findByName(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
//<---------------- List ---------------------->
	public Page<Tracking> findPaginatedJobTracking(int pageNo, int pageSize) {
//		Pageable pageable = PageRequest.of(pageNo, pageSize);
//		return jobTrackingRepository.findAll(pageable);
		return null;
	}

	

	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	public List<Tracking> addTracking(JobTrackingDTO jobTracking, long id) {
		return null;
		
	}


	public User findConnectedUser(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	public void findByUser() {
		
		
		
	}


	

}
