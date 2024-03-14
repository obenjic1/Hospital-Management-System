package com.ppp.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppp.user.model.Role;
import com.ppp.user.repository.RoleRepository;
import com.ppp.user.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	 
	@Value("${paginationSise}")
	private int paginationRoleSize;
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private RoleService roleService;
	
	@PreAuthorize("hasAuthority('ROLE_LIST_ROLES')")
	@GetMapping("/list-roles")
	public String listAlltheRoles(Model model) {
		
		return findPaginated(1, model);		
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
	    int pageSize = paginationRoleSize;
	    Page < Role > page = roleService.findPaginated(pageNo, pageSize);
	    List < Role > getAllRoles = page.getContent();
	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    model.addAttribute("roles", getAllRoles);
	    return "user/list-roles";
	}
	
	@PreAuthorize("hasAuthority('ROLE_VIEW_ROLE_DETAILS')")
	@GetMapping("/view-role-details/{name}")
	public String viewRoleDetails(@PathVariable String name, Model model) {
			Role roleFind = roleRepository.findByName(name);
			model.addAttribute("roleFind", roleFind);
	    return "user/role-details";
	}

}
