package com.ppp.billing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.ActivityLog;
import com.ppp.billing.repository.ActivityLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityLogService {
	
	  private final ActivityLogRepository activityLogRepository;

	    public ActivityLog save(ActivityLog log) {
	        return activityLogRepository.save(log);
	    }

	    public List<ActivityLog> findAll() {
	        return activityLogRepository.findAll();
	    }

	    public void deleteById(Long id) {
	        activityLogRepository.deleteById(id);
	    }

}
