package com.ppp.user.service;


import java.util.Optional;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.data.domain.Page;

import com.ppp.user.model.User;
import com.ppp.user.model.dto.UserDTO;

public interface UserService {
	Iterable< User> getAllUser();
	void deleteUserByUsername(Long id);
	User findUserByUsername(String username);
	User updateUser(User updatedUser, Long id);
	Page< User > findPaginatedUser(int pageNo, int pageSize);
	String createUser(UserDTO userDTO) throws Exception;
	void updateResetPasswordToken(String token, String email) throws AccountNotFoundException;
	User getByResetPasswordToken(String token);
	void updatePassword(User user, String newPassword);
	void enableUser(long id);
	Optional<User> findById(long id);
}

