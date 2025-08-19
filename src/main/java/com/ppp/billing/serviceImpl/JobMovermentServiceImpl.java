package com.ppp.billing.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.Department;
import com.ppp.billing.model.JobMovement;
import com.ppp.billing.model.JobTracking;
import com.ppp.billing.model.dto.JobMovementDTO;
import com.ppp.billing.repository.DepartmentRepository;
import com.ppp.billing.repository.JobMovementRepository;
import com.ppp.billing.service.JobMovementService;
import com.ppp.user.model.User;
import com.ppp.user.repository.UserRepository;

@Service
public class JobMovermentServiceImpl implements JobMovementService{
	@Autowired
	JobMovementRepository jobMovementRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<JobMovement> findAll() {
		return jobMovementRepository.findAll();
	}

	
	@Override
	public String movejob(long id, JobMovementDTO jobMovementDTO) {
		
		
		
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(name);
		
		return "OK";
	}


	@Override
	public JobMovement saveMovement(JobMovementDTO jobMovementDTO) {
		JobMovement jobMovement = new JobMovement();
		jobMovement.setCreationDate(new Date());
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(name);
		jobMovement.setUser(user);
		jobMovement.setDescription(jobMovementDTO.getDescription());
		jobMovement.setDepartment(departmentRepository.findById(jobMovementDTO.getDepartment()));
		
		return jobMovementRepository.save(jobMovement);
	}}
