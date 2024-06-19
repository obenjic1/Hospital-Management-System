package com.ppp.billing.serviceImpl;

import com.ppp.billing.model.JobEstimate;
import com.ppp.billing.repository.JobEstimateRepository;
import com.ppp.billing.service.JobEstimateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class JobEstimateServiceImpl implements JobEstimateService {
	

	@Autowired
	private JobEstimateRepository jobEstimateRepository;


	@Override
	public JobEstimate save(JobEstimate jobEstimate) {
		return jobEstimateRepository.saveAndFlush(jobEstimate);
	}
//
//	@Override
//	public JobEstimate saveEstimate(String quantity, float advancePercentage) {
//		JobEstimate Estimate = new JobEstimate();
//		Estimate.setAdvancePercentage(advancePercentage);
//		return jobEstimateRepository.saveAndFlush(Estimate);
//	}
}
