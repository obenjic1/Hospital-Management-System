package com.ppp.user.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ppp.user.model.Role;
import com.ppp.user.repository.RoleRepository;
import com.ppp.user.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	
		@Override
	public List<Role> getAllRoles() {
		try {
			return roleRepository.findAll();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Page<Role> findPaginated(int pageNo, int pageSize) {
		try {
			 Pageable pageable = PageRequest.of(pageNo - 1, pageSize);		 
			 return roleRepository.findAll(pageable);
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@Override
	public Role viewRoleDetails(String name) {
		try {
			return roleRepository.findByName(name);
		} catch (Exception e) {
			throw e;
		}
	}
}
