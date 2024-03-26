package com.ppp.user.service;


import javax.security.auth.login.AccountNotFoundException;

import org.springframework.data.domain.Page;

import com.ppp.user.model.User;
import com.ppp.user.model.dto.UserDTO;

public interface UserService {
	Iterable< User > getAllUser(boolean isDeleted);
	void deleteUserByUsername(Long id);
	User findUserByUsername(String username);
	String updateUser(User updatedUser, Long id);
	Page< User > findPaginatedUser(int pageNo, int pageSize, boolean isDeleted);
	String createUser(UserDTO userDTO);
	void updateResetPasswordToken(String token, String email) throws AccountNotFoundException;
	User getByResetPasswordToken(String token);
	void updatePassword(User user, String newPassword);
}

