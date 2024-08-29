package com.ppp.billing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.Department;
@Service
public interface DepartmentService {
	Department findById(long id);
	List<Department> findAll();

}
