package com.ppp.user.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ppp.user.model.Role;

public interface RoleService {
	
	List<Role> getAllRoles();
	Role viewRoleDetails(String name);
	Page < Role > findPaginated(int pageNo, int pageSize);

}
