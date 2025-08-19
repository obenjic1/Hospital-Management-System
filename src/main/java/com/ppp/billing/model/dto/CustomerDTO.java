package com.ppp.billing.model.dto;

import java.util.Date;
import org.springframework.web.multipart.MultipartFile;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
	
	private String name;
	private String email;
	private String telephone;
	private String address;
	private MultipartFile thumbnail;
	private Date creationDate;

}
