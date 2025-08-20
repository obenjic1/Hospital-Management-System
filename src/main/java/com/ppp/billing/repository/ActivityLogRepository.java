package com.ppp.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.ActivityLog;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {}
