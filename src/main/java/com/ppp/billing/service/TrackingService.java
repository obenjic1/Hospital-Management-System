package com.ppp.billing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.ppp.billing.model.Tracking;
import com.ppp.billing.model.dto.JobTrackingDTO;
import com.ppp.user.model.User;


public interface TrackingService {
	
	Optional<Tracking> findByName(String name);
	Page< Tracking > findPaginatedJobTracking(int pageNo, int pageSize);
	void delete(long id);
	Tracking save(JobTrackingDTO jobTrackingDTO, long id);
	List<Tracking> addTracking (JobTrackingDTO jobTracking,long id);
	User findConnectedUser (String username);
	

}
