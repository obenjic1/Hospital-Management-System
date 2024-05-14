package com.ppp.billing.model.dto;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrintingMachineDTO {

	private String name;
	private int plateLength; 
	private int plateWidth; 
	private boolean isActive;
	private String abbreviation; 
	private String thumbnail;
	private MultipartFile imageFile;
	private Date creation_date;
	private List<JobPaperDTO> jobPaperDTO;
	
	
}
