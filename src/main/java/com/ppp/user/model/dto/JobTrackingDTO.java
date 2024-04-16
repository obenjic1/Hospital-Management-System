package com.ppp.user.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobTrackingDTO {
	
	private Date operation;
	private Date creationDate;

}
