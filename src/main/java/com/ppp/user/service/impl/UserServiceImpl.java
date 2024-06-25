package com.ppp.user.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.security.auth.login.AccountNotFoundException;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	private EntityManager entityManager;
	@Autowired
	private FileStorageService fileStorageService;

//<---------------- List user ---------------------->
	@Override
	public Iterable<User> getAllUser(boolean isDeleted) {
	    
	    return userRepository.findAll();
	}
	
	@Override
	public Page<User> findPaginatedUser(int pageNo, int pageSize, boolean isDeleted) {
		try {
			Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
			Session session = entityManager.unwrap(Session.class);
		    Filter filter = session.enableFilter("deletedUsertFilter");
		    filter.setParameter("isDeleted", isDeleted);
		    Iterable<User> users = userRepository.findAll();
		    session.disableFilter("deletedUserFilter");
			return userRepository.findAll(pageable);
		} catch (Exception e) {
			throw e;
		}
	}

//<-------------------- Create user using userDTO ---------------------->
	@Override
	public String createUser(UserDTO userDTO) {
		User newUser = new User();
	    newUser.setUsername(userDTO.getUsername());
	    newUser.setFirstName(userDTO.getFirstName());
	    newUser.setLastName(userDTO.getLastName());
	    newUser.setAddress(userDTO.getAddress());
	    newUser.setEmail(userDTO.getEmail());
	    newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
	    newUser.setMobile(userDTO.getMobile());
	    newUser.setCreatedAt(LocalDate.now());
	    String getGroup = userDTO.getGroupe();
	    Groupe groupe = groupeRepository.findByName(getGroup);
	    newUser.setGroupe(groupe);
	    List< User > userToCompare = userRepository.findAll();
	    for(User user : userToCompare) {
	    	if(user.getUsername().equals(newUser.getUsername()) || user.getEmail().equals(newUser.getEmail()))
	    		return "error";
	    }
	    
	    if (userDTO.getImageFile() != null && !userDTO.getImageFile().isEmpty()) {
            try {
                String imagePath = fileStorageService.storeUserFile(userDTO.getImageFile());
                newUser.setImagePath(imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	   userRepository.save(newUser);
	   return "sucess";
	}


	@Override
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
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
			userRepository.deleteById(id);
	}
	//<--------------------- Delete User By Id -------------------------->
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}
//<----------------- Get user by user name using DTO object ------------------->	
	public UserDTO getUserByUsername(String username) {
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
    }

//<---------------------- Update user ---------------------> 
	@Override
	public String updateUser(User updatedUser, Long id) {
		try {
			User existingUser = userRepository.findById(id).get();
			if(existingUser == null) {
				return " error";		
			}
			existingUser.setFirstName(updatedUser.getFirstName());
			existingUser.setLastName(updatedUser.getLastName());
			existingUser.setUsername(updatedUser.getUsername());
			existingUser.setEmail(updatedUser.getEmail());
			existingUser.setAddress(updatedUser.getAddress());
			existingUser.setMobile(updatedUser.getMobile());
			 userRepository.save(existingUser);

			return " success";
		} catch (Exception e) {
			throw e;
		}
	}

	
	
	public void updateResetPasswordToken(String token, String email) throws AccountNotFoundException {
		User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new AccountNotFoundException("Could not find any worker with the email " + email);
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
		
	}


