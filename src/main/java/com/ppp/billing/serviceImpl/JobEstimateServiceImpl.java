package com.ppp.billing.serviceImpl;

import com.ppp.billing.model.EstimatePricing;
import com.ppp.billing.model.JobEstimate;
import com.ppp.billing.repository.JobEstimateRepository;
import com.ppp.billing.service.JobEstimateService;

import java.util.ArrayList;
import java.util.List;

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


	@Override
	public JobEstimate findById(long id) {
		// TODO Auto-generated method stub
		return jobEstimateRepository.findById(id).get();
	}


	@Override
	public List<EstimatePricing> generateCommissionEstimate(long id, double commission, double discount) {
		JobEstimate estimate = jobEstimateRepository.findById(id).get();
		List<EstimatePricing> estimqtePricing = estimate.getEstimatePricings();
		estimate.setCommission(commission);
		estimate.setDiscountValue(discount);
		List<EstimatePricing> estimateP = new ArrayList<EstimatePricing>();
		for(EstimatePricing estimatePrice :estimqtePricing) {
			EstimatePricing estimateCommision = new EstimatePricing();
			estimateCommision.setQuantity(estimatePrice.getQuantity());
			estimateCommision.setTotalPrice(estimatePrice.getTotalPrice()+estimate.getCommission()+estimate.getDiscountValue());
			estimateCommision.setUnitPrice((estimatePrice.getTotalPrice()+estimate.getCommission()+estimate.getDiscountValue())/estimatePrice.getQuantity());
			estimateP.add(estimateCommision);
		}
		
		return estimateP;
	}
}
