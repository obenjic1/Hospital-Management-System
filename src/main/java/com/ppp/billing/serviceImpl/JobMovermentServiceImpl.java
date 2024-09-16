package com.ppp.billing.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.Department;
import com.ppp.billing.model.Job;
import com.ppp.billing.model.JobMovement;
import com.ppp.billing.model.JobTracking;
import com.ppp.billing.model.dto.JobMovementDTO;
import com.ppp.billing.repository.DepartmentRepository;
import com.ppp.billing.repository.JobMovementRepository;
import com.ppp.billing.repository.JobRepository;
import com.ppp.billing.repository.JobTrackingRepository;
import com.ppp.billing.service.JobMovementService;
import com.ppp.user.model.User;
import com.ppp.user.repository.UserRepository;

@Service
public class JobMovermentServiceImpl implements JobMovementService{
	@Autowired
	JobMovementRepository jobMovementRepository;
	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	JobTrackingRepository jobTrackingRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<JobMovement> findAll() {
		return jobMovementRepository.findAll();
	}

	
	@Override
	public String movejob(long id, JobMovementDTO jobMovementDTO) {
		Job job =jobRepository.findById(id).get();
		
		List<JobMovement> movements = job.getJobMovements();
		JobMovement moveJob = new JobMovement();
		moveJob.setCreationDate(new Date());
		moveJob.setDescription(jobMovementDTO.getDescription());
		Department	department = departmentRepository.findById(jobMovementDTO.getDepartment());
		moveJob.setDepartment(department);
		movements.add(moveJob);
		job.setJobMovements(movements);
		moveJob.setJob(job);
		
//		List<JobTracking> jobTrackings = job.getJobTrackings() ;
//		JobTracking tracking = new JobTracking();
//		tracking.setCreationDate(new Date());
//		tracking.setOperation("send job to " + department.getName()+ " ( " + moveJob.getDescription() +" )" );
//		String name = SecurityContextHolder.getContext().getAuthentication().getName();
//		User user = userRepository.findByUsername(name);
//		tracking.setUser(user);
//		tracking.setJob(job);
//		jobTrackings.add(tracking);
//		jobTrackingRepository.saveAll(jobTrackings);
		jobMovementRepository.saveAll(movements);
		return "OK";
	}


	@Override
	public JobMovement saveMovement(JobMovementDTO jobMovementDTO) {
		JobMovement jobMovement = new JobMovement();
		jobMovement.setCreationDate(new Date());
		jobMovement.setDescription(jobMovementDTO.getDescription());
		jobMovement.setDepartment(departmentRepository.findById(jobMovementDTO.getDepartment()));
		
		return jobMovementRepository.save(jobMovement);
	}}
