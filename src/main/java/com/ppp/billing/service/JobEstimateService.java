package com.ppp.billing.service;

import com.ppp.billing.model.EstimatePricing;
import com.ppp.billing.model.JobEstimate;

import java.util.List;

public interface JobEstimateService {

	JobEstimate save(JobEstimate jobEstimate);
	JobEstimate findById(long id);
	List<EstimatePricing> generateCommissionEstimate(long id,double commission,double discount);
		

}
