package com.ppp.user.model.dto;

import java.util.Date;

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
	private String thumbnail;
	private Date creationDate;

}
