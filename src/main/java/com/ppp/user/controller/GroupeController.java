package com.ppp.user.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppp.user.model.Groupe;
import com.ppp.user.model.Role;
import com.ppp.user.model.dto.GroupDTO;
import com.ppp.user.repository.RoleRepository;
import com.ppp.user.service.GroupeService;
import com.ppp.user.service.impl.GroupeServiceImpl;

@Controller
@RequestMapping("/group")
public class GroupeController {
	
	@Value("${paginationSise}")
	private int paginationGroupSize;


	@Autowired
	private GroupeService groupeService;
	@Autowired
	private GroupeServiceImpl groupeServiceImpl;
	@Autowired
	private RoleRepository roleRepository;


//<------------------- Call a new group form -------------------->
	@GetMapping("/add-group")
	public String showGroupeRegistrationForm(Model model) {
		List<Role> roles = roleRepository.findAll();
		model.addAttribute("roles", roles);
		model.addAttribute("groupDTO", new GroupDTO());
		return "user/add-group";
	}

//<------------------- Add a new group -------------------->
	@PostMapping("/add-group")
	public String saveGroupe(@RequestBody GroupDTO groupDTO, String name) throws Exception {
		try {
			groupeService.addNewGroupe(groupDTO, name);
			return "user/list-groups";
		} catch (Exception e) {
			throw e;
		}


	}

//<------------------- List groups -------------------->
	@GetMapping("/list-groups")
	public String listAllGroupes(Model model) {
		return findPaginatedGroups(1, model);
	}

//<------------------- List groups with pagination -------------------->
	@GetMapping("/page/{pageNo}")
	public String findPaginatedGroups(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = paginationGroupSize;

		Page<Groupe> page = groupeService.findPaginatedGroup(pageNo, pageSize);
		List<Groupe> groups = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("groups", groups);
		return "user/list-groups";
	}

//<------------------- View group details -------------------->
	@GetMapping("/group-details/{name}")
	public String getGroupeDetails(@PathVariable String name, Model model) {
		List<Role> roles = roleRepository.findAll();
		Groupe existingGroupe = groupeService.groupDetails(name);
		if (existingGroupe == null) {
			return "grou/group-not-found";
		}
		model.addAttribute("existingGroupe", existingGroupe);
		model.addAttribute("roles", roles);
		return "user/group-details";
	}

	// <------------------- View group details To update -------------------->
	@GetMapping("/update-group/{name}")
	public String getGroupeDetailsToUpdate(@PathVariable String name, Model model) {
		List<Role> roles = roleRepository.findAll();
		Groupe groupeFinded = groupeService.groupDetails(name);
		if (groupeFinded == null) {

			return "grou/group-not-found";
		}
		model.addAttribute("groupeFinded", groupeFinded);
		model.addAttribute("roles", roles);
		return "user/update-group";
	}
	
	@PostMapping("/disable-group/{id}")
 public ResponseEntity<String> enable(@PathVariable long id) {
		try {
			groupeServiceImpl.disableGroup(id);
			return new ResponseEntity<String>("Success", HttpStatus.OK);
				} catch (Exception e) {
					return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
				}
		}

//<------------------- Update group -------------------->
	@PostMapping("/update-group/{name}")
	public String updateGroup(@PathVariable String name, @RequestBody GroupDTO groupDTO) {
		String updateGroup = groupeService.updateGroupe(groupDTO, name);
		if (updateGroup == "error") {
			return "error";
		}
		return "user/list-groups";
	}
	
}
