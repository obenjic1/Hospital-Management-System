package com.ppp.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
	

}
