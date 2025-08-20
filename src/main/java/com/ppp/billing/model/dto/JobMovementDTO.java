package com.ppp.billing.model.dto;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

import com.ppp.billing.model.Department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobMovementDTO {
	
	@Column(name = "description")
	private String description;
	
	private long department;

	
}
