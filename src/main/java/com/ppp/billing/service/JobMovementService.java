package com.ppp.billing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.JobMovement;

@Service
public interface JobMovementService {
	
	List<JobMovement> findAll();
}
