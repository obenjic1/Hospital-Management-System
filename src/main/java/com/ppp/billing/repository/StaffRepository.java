package com.ppp.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.Department;
import com.ppp.billing.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
	

	    List<Staff> findByDepartment(Department department);
	    List<Staff> findByisActiveTrue();
	
}
