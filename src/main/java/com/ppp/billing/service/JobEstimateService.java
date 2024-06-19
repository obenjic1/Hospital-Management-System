package com.ppp.billing.service;

import com.ppp.billing.model.JobEstimate;
import com.ppp.billing.model.dto.EstimateDTO;

import java.util.List;

public interface JobEstimateService {

	JobEstimate save(JobEstimate jobEstimate);
//	JobEstimate saveEstimate (String quantity,float advancePercentage);

}
