package com.ppp.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppp.billing.model.Department;
@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
	Department findById(long id);

}
