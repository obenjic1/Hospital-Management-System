package com.ppp.billing.model.dto;

import org.springframework.web.multipart.MultipartFile;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDto {
	
	
private long id;
private String oldpassword;
private String newpassword;
private MultipartFile imageFile;



}
