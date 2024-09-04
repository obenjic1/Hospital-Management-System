package com.ppp.billing.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.Department;
import com.ppp.billing.model.Job;
import com.ppp.billing.model.JobMovement;
import com.ppp.billing.model.dto.JobMovementDTO;
import com.ppp.billing.repository.DepartmentRepository;
import com.ppp.billing.repository.JobMovementRepository;
import com.ppp.billing.repository.JobRepository;
import com.ppp.billing.service.JobMovementService;

@Service
public class JobMovermentServiceImpl implements JobMovementService{
	@Autowired
	JobMovementRepository jobMovementRepository;
	@Autowired
	JobRepository jobRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	
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
		moveJob.setJob(job);
		job.setJobMovements(movements);
		return "OK";
	}}
