package com.ppp.billing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.JobMovement;
import com.ppp.billing.model.dto.JobMovementDTO;

@Service
public interface JobMovementService {
	
	List<JobMovement> findAll();
	String movejob (long id,JobMovementDTO jobMovementDTO);
	JobMovement saveMovement (JobMovementDTO jobMovementDTO);
}
