package com.ppp.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.EstimatePricing;

public interface EstimatePricingRepository extends JpaRepository<EstimatePricing, Long> {

}
