package com.ppp.billing.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.Department;
import com.ppp.billing.repository.DepartmentRepository;
import com.ppp.billing.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	DepartmentRepository departmentRepository;
	
	@Override
	public Department findById(long id) {
		
		return departmentRepository.findById(id);
	}

	@Override
	public List<Department> findAll() {
		
		return departmentRepository.findAll();
	}

}
