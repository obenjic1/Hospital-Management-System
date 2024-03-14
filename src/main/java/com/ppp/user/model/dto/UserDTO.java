package com.ppp.user.model.dto;




import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String mobile;
	private String address;
	private String username;
	private MultipartFile imageFile;
	private String groupe;
}


