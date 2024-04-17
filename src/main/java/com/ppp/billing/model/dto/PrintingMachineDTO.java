package com.ppp.billing.model.dto;

import java.util.Date;
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
	private boolean is_active = Boolean.TRUE;
	private String thumbnail;
	private Date creation_date;
}
