package com.ppp.billing.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
	
	private int customerId;
	private int jobTypeId;
	private String title;
	private int coverVolume;
	private int contentVolume;
	private int  ctpFees;
	private double openWidth;
	private double  openLength;
	private double  closeWidth;
	private double  closeLength;
	private boolean  existingPlate;
	private boolean  dataSuppliedByCustomer;
	private boolean  layOutByUs;
	private boolean  typesettingByUs;
	private JobActivityOptionDTO jobActivities;
	private List<JobPaperDTO> jobPapers;
}
