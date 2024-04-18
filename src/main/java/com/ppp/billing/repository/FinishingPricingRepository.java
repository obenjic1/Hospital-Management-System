package com.ppp.billing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ppp.billing.model.FinishingPricing;

public interface FinishingPricingRepository extends JpaRepository<FinishingPricing, Long> {
	
	Optional<FinishingPricing>  findById(long Id );
}
