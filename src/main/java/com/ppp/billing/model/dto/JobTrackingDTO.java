package com.ppp.billing.model.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobTrackingDTO {

	
	private String operation;
	
	private Date creationDate;
	
	private long userId;
	
	private long  jobId;
}
