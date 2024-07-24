package com.ppp.billing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.EstimatePricing;
@Service
public interface EstimatePricingServiice {
	
	Optional<EstimatePricing> findById(long id);
	List<EstimatePricing> save(List<EstimatePricing> estimatePricing);

}
