package com.ppp.billing.service;

import java.util.List;

import com.ppp.billing.model.Job;
import com.ppp.billing.model.dto.JobDTO;

public interface JobService {

	Job saveJob(JobDTO jobDTO);
	List<Job> listAllJob();


}
