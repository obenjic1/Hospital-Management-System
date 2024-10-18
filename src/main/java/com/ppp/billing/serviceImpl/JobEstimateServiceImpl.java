package com.ppp.billing.serviceImpl;

import com.ppp.billing.model.EstimatePricing;
import com.ppp.billing.model.JobEstimate;
import com.ppp.billing.repository.JobEstimateRepository;
import com.ppp.billing.service.JobEstimateService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
				estimateCommision.setTotalPrice(estimatePrice.getTotalPrice()+ estimate.getCommission());
				estimateCommision.setUnitPrice((estimatePrice.getTotalPrice()+estimate.getCommission())/estimatePrice.getQuantity());
			estimateP.add(estimateCommision);
		}
		
		jobEstimateRepository.save(estimate);
	
		return estimateP;
	}


	public List<EstimatePricing> generateCommissionEstimateResult(long id) {
		JobEstimate estimate = jobEstimateRepository.findById(id).get();
		List<EstimatePricing> estimqtePricing = estimate.getEstimatePricings();
		List<EstimatePricing> estimateP = new ArrayList<EstimatePricing>();
		for(EstimatePricing estimatePrice :estimqtePricing) {
			EstimatePricing estimateCommision = new EstimatePricing();
			estimateCommision.setQuantity(estimatePrice.getQuantity());
			estimateCommision.setTotalPrice(estimatePrice.getTotalPrice()+ estimate.getCommission());
			estimateCommision.setUnitPrice((estimatePrice.getTotalPrice()+estimate.getCommission())/estimatePrice.getQuantity());
			estimateP.add(estimateCommision);
		}

		return estimateP;
	}

	public List<EstimatePricing> generateDiscountCommissionEstimateResult(long id) {
		JobEstimate estimate = jobEstimateRepository.findById(id).get();
		List<EstimatePricing> estimqtePricing = estimate.getEstimatePricings();
		estimate.setDiscountValue(estimate.getDiscountValue());
		List<EstimatePricing> estimateP = new ArrayList<EstimatePricing>();
		
		
		for(EstimatePricing estimatePrice :estimqtePricing) {
			EstimatePricing estimateCommision = new EstimatePricing();
			estimateCommision.setQuantity(estimatePrice.getQuantity());
			estimateCommision.setTotalPrice(estimatePrice.getTotalPrice()-estimate.getDiscountValue());
			estimateCommision.setUnitPrice((estimatePrice.getTotalPrice()-estimate.getDiscountValue())/estimatePrice.getQuantity());
			estimateP.add(estimateCommision);
		}
		
		return estimateP;
	}
	

	@Override
	public Optional<JobEstimate> findByReferenceNumber(String referenceNumber) {
		Optional<JobEstimate> estimate = jobEstimateRepository.findByReference(referenceNumber.toUpperCase());
		return estimate ;
	}
}
