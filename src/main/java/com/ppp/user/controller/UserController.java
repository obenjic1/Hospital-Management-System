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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ppp.user.model.Groupe;
import com.ppp.user.model.User;
import com.ppp.user.model.dto.UserDTO;
import com.ppp.user.repository.GroupeRepository;
import com.ppp.user.repository.UserRepository;
import com.ppp.user.service.impl.UserServiceImpl;

@Controller
@RequestMapping("user")

public class UserController {

	@Value("${paginationSise}")
	private int paginationRoleSize;
//<----------------- Injection of dependences --------------------->
	
	@Autowired
	private GroupeRepository groupeRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserServiceImpl userServiceImpl;

//<------------------- Get add user form ---------------------->
 	@PreAuthorize("hasAuthority('ROLE_LIST_USERS')")
	@GetMapping("/add-user")
	public String showRegistrationForm(Model model ,String name) {
		List<Groupe> groups = groupeRepository.findAll();
		model.addAttribute("groups", groups);
		model.addAttribute("userDTO", new UserDTO());
		return "user/add-user";
	}
 	
//<----------------------- persist user in to database ------------------>

 	@PreAuthorize("hasAuthority('ROLE_LIST_USERS')")
 	@PostMapping("/add-user")
	public String saveUser(UserDTO userDTO, @RequestParam("imageFile") MultipartFile getImageFile, String username, String email) throws Exception {
 		String registeredUser = userServiceImpl.createUser(userDTO);

 		if(registeredUser.equals("error")) {
			throw new Exception("Username or Email already exist");
		}
		return "user/list-users";
	}
	
//<------------------- View user profile -------------------->
	@PreAuthorize("hasRole('ROLE_VIEW_USER_DETAILS')")
	@GetMapping("/viewUser/{username}")
	public String findOneUser(@PathVariable String username, Model model) throws Exception {
			try {
				User userFind = userServiceImpl.findUserByUsername(username);
				if(userFind  == null) {
					return "errors/user-not-found";
				}
				model.addAttribute("user", userFind);
		    return "user/view-user-profile";
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
	}
	

	
	//<------------------- View user profile -------------------->
	@PreAuthorize("hasRole('ROLE_UPDATE_USER')")
	@GetMapping("/get-user/{username}")
		public String getUserByUsername(@PathVariable String username, Model model) throws Exception {
				List<Groupe> groups = groupeRepository.findAll();
				User userFinded = userServiceImpl.findUserByUsername(username);
				if(userFinded  == null) {
					throw new Exception("something wher wrong");
				}
				model.addAttribute("userFinded", userFinded);
				model.addAttribute("groups", groups);
		    return "user/update-user";
		}
	
	
//<---------------------- Update user ------------------------->	
	@PreAuthorize("hasRole('ROLE_LIST_USERS')")
	@PostMapping("/update-user/{id}")
	public ResponseEntity<String> updateUser(@RequestBody User updatedUser, @PathVariable Long id) throws Exception {
		try {
	    User userUpdated = userServiceImpl.updateUser(updatedUser, id);
	    
	    return new ResponseEntity<String>("Success", HttpStatus.OK);
	} catch (Exception e) {			
		return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
	}
	}

	
//<---------------------- Get list of users ---------------------->
	@PreAuthorize("hasRole('ROLE_LIST_USERS')")
	@GetMapping("/list-users")
	public String listUsers(Model model) {
		return findPaginatedUser(1, false, model);
	}

//<---------------------- Get list of users with pagination ---------------------->
	@GetMapping("/page/{pageNo}")
	public String findPaginatedUser(@PathVariable(value = "pageNo") int pageNo, boolean isDeleted, Model model) {
	    int pageSize = paginationRoleSize;

	    Page < User > page = userServiceImpl.findPaginatedUser(pageNo, pageSize, isDeleted );
	    List < User > listOfUser = page.getContent();

	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    model.addAttribute("users", listOfUser);
	    return "user/list-users";
	}

//<--------------------- Remove user Using soft delete ----------------->
@PreAuthorize("hasRole('ROLE_REMOVE_USER')")
	@PostMapping("remove-user/{id}")
	public void removeUserByUsername(@PathVariable Long id) {
	    User existingUser = userRepository.findById(id).get();
	    if (existingUser == null) {
	      return ;
	    }
	userServiceImpl.deleteUserById(id);
	    return ;
	  }

//<----------------------Enable and Disable a User --------------------->
@PostMapping("/enable/{id}")
 public ResponseEntity<String> enable(@PathVariable long id) {
	try {
		userServiceImpl.enableUser(id);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
			}
	}

}
