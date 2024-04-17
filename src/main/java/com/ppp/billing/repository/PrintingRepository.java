package com.ppp.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.PrintingPricing;


public interface PrintingRepository extends JpaRepository<PrintingPricing, Long> {

}
