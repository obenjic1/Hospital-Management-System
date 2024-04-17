package com.ppp.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.FinishingPricing;

public interface FinishingRepository extends JpaRepository<FinishingPricing, Long> {

}
