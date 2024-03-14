package com.ppp.user.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Session session = entityManager.unwrap(Session.class);
	    Filter filter = session.enableFilter("deletedUsertFilter");
	    filter.setParameter("isDeleted", isDeleted);
	    Iterable<User> users = userRepository.findAll();
	    session.disableFilter("deletedUserFilter");
		return userRepository.findAll(pageable);
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
                String imagePath = fileStorageService.storeFile(userDTO.getImageFile());
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

//<--------------------- Delete User --------------------------> 
	@Override
	public void deleteUserByUsername(Long id) {
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
	}

}
