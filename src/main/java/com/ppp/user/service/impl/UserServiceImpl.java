package com.ppp.user.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ppp.user.model.Groupe;
import com.ppp.user.model.User;
import com.ppp.user.model.dto.UserDTO;
import com.ppp.user.repository.GroupeRepository;
import com.ppp.user.repository.UserRepository;
import com.ppp.user.service.FileStorageService;
import com.ppp.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

//<----------------- Injection of  dependences ----------------------> 
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private GroupeRepository groupeRepository;
	@Autowired
	private FileStorageService fileStorageService;

//<---------------- List user ---------------------->
	@Override
	public Iterable<User> getAllUser() {
	    
	    return userRepository.findAll();
	}
	
	@Override
	public Page<User> findPaginatedUser(int pageNo, int pageSize) {
		try {
			Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
			return userRepository.findAll(pageable);
		} catch (Exception e) {
			throw e;
		}
	}

//<-------------------- Create user using userDTO ---------------------->
	@Override
	public String createUser(UserDTO userDTO) throws IllegalStateException, IOException {
		try {
			User newUser = new User();
		    newUser.setUsername(userDTO.getUsername());
		    newUser.setFirstName(userDTO.getFirstName());
		    newUser.setLastName(userDTO.getLastName());
		    newUser.setAddress(userDTO.getAddress());
		    newUser.setEmail(userDTO.getEmail());
		    newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		    newUser.setConfirmPassword(passwordEncoder.encode(userDTO.getConfirmPassword()));
		    newUser.setMobile(userDTO.getMobile());
		    newUser.setCreatedAt(new Date());
		    String getGroup = userDTO.getGroupe();
		    Groupe groupe = groupeRepository.findByName(getGroup);
		    newUser.setGroupe(groupe);
		    List< User > userToCompare = userRepository.findAll();
		    for(User user : userToCompare) {
		    	if(user.getUsername().toLowerCase().equals(newUser.getUsername().toLowerCase()) || user.getEmail().equals(newUser.getEmail()))
		    		return "error";
		    }
		    if (userDTO.getImageFile() != null && !userDTO.getImageFile().isEmpty()) {
		    	
		                String imagePath = fileStorageService.storeUserFile(userDTO.getImageFile());
		                newUser.setImagePath(imagePath);
		          
	        } 
		   userRepository.save(newUser);
		   return "sucess";
		} catch (Exception e) {
			throw e;
		}
	}


	@Override
	public User findUserByUsername(String username) {
		try {
			return userRepository.findByUsername(username);
		} catch (Exception e) {
			throw e;
		}
	}
	
	//<--------------------- find user by email ojong--------------------------> 
	public User findUserByEmail(String email) {
		try {
			return userRepository.findByEmail(email);
		} catch (Exception e) {
			throw e;
		}
	}

	
//<--------------------- Delete User --------------------------> 
	@Override
	public void deleteUserByUsername(Long id) {
			userRepository.findById(id).get();
			
	}
	//<--------------------- Delete User By Id -------------------------->
	public void deleteUserById(Long id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			throw e;
		}
	}
//<----------------- Get user by user name using DTO object ------------------->	
	public UserDTO getUserByUsername(String username) throws Exception {
       try {
    	   User user = userRepository.findByUsername(username);
           if (user == null) {
               return null;
           }
           UserDTO userDTO = new UserDTO();
           userDTO.setFirstName(user.getFirstName());
           userDTO.setLastName(user.getLastName());
           userDTO.setAddress(user.getAddress());
           userDTO.setMobile(user.getMobile());
           userDTO.setMobile(user.getMobile());
           userDTO.setPassword(user.getPassword());
           userDTO.setUsername(user.getUsername());
           userDTO.setEmail(user.getEmail());
          

           return userDTO;
	} catch (Exception e) {
		throw e;
	}
    }

//<---------------------- Update user ---------------------> 
	@Override
	public User updateUser(UserDTO user, Long id) {
		
			User existingUser = userRepository.findById(id).get();
			existingUser.setFirstName(user.getFirstName());
			existingUser.setLastName(user.getLastName());
//			existingUser.setUsername(updatedUser.getUsername());
			existingUser.setEmail(user.getEmail());
			existingUser.setAddress(user.getAddress());
			existingUser.setMobile(user.getMobile());
			 if (user.getImageFile() != null && !user.getImageFile().isEmpty()) {
				   
	            
				try {
					String imagePath = fileStorageService.storeUserFile(user.getImageFile());
					 existingUser.setImagePath(imagePath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	              
	         
	   } 
			userRepository.save(existingUser);
			return existingUser;
		
	
	}


	public void updateResetPasswordToken(String token, String email) throws AccountNotFoundException {
		try {
			User user = userRepository.findByEmail(email);
	        if (user != null) {
	            user.setResetPasswordToken(token);
	            userRepository.save(user);
	        } else {
	            throw new AccountNotFoundException("Could not find any worker with the email " + email);
	        }
		} catch (Exception e) {
			throw e;
		}
    }

	
	
		public User getByResetPasswordToken(String token) {
		return userRepository.findByResetPasswordToken(token);
	}


	public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
         
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }
	
	@Override
	public void  enableUser(long id) {	
		User user = userRepository.findById(id).get();
		if(user.isActive()) {
			user.setActive(false);
			userRepository.save(user);
		}
		else {
			user.setActive(true);	
			userRepository.save(user);
			}
		}

public User getLogedUser(HttpServletRequest httpServletRequest){
		
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		
		if(securityContext == null)
			return null;
		
		String userName = securityContext.getAuthentication().getName();
		List<String> roles = new ArrayList<>();
		
		for(GrantedAuthority ga:securityContext.getAuthentication().getAuthorities()){
			roles.add(ga.getAuthority());
		}
		
		User user = userRepository.findByUsername(userName);
		
		return user;
}


@Override
public Optional<User> findById(long id) {
	// TODO Auto-generated method stub
	return userRepository.findById(id);
}
	
	}


