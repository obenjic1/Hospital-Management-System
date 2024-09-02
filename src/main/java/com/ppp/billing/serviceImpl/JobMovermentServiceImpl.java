package com.ppp.billing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.JobMovement;
import com.ppp.billing.repository.JobMovementRepository;
import com.ppp.billing.service.JobMovementService;

@Service
public class JobMovermentServiceImpl implements JobMovementService{
	@Autowired
	JobMovementRepository jobMovementRepository;
	
	@Override
	public List<JobMovement> findAll() {
		return jobMovementRepository.findAll();
	}

}
