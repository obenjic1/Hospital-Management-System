package com.ppp.billing.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.StockRequest;

public interface StockRequestRepository extends JpaRepository<StockRequest, Long> {
    List<StockRequest> findByFulfilledFalse();
}
