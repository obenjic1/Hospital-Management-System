package com.ppp.billing.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentTypeDTO {
	
	private String name;
	private List<String> jobPapers;
}
