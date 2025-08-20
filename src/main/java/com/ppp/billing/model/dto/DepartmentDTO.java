package com.ppp.billing.model.dto;

import java.util.List;

import javax.persistence.Column;

import com.ppp.billing.model.Department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

	@Column(name = "name",length = 255)
	private String name;
	
}
