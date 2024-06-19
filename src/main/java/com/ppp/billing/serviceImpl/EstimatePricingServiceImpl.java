package com.ppp.billing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.EstimatePricing;
import com.ppp.billing.repository.EstimatePricingRepository;
import com.ppp.billing.service.EstimatePricingServiice;
@Service
public class EstimatePricingServiceImpl implements EstimatePricingServiice {
	@Autowired
	private EstimatePricingRepository estimatePricingRepository;
	@Override
	public EstimatePricing findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EstimatePricing> save(List<EstimatePricing> estimatePricing) {
		
		return estimatePricingRepository.saveAll(estimatePricing);
	}

	

//	@Override
//	public JobEstimate save(JobEstimate jobEstimate) {
//		return jobEstimateRepository.saveAndFlush(jobEstimate);
//	}


	
}
