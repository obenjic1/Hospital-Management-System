package com.ppp.user.model.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private String password;
	private String confirmPassword;
	private String username;
	private String function;
	private MultipartFile imageFile;
	private String groupe;
	private String resetPasswordToken;
	private String email;
	private long staff;
}


