package com.ppp.billing.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
	private int  id;
	private int customerId;
	private int jobTypeId;
	private String title;
	private String paperFormat;
	private int coverVolume;
	private int contentVolume;
	private int  ctpFees;
	private int openWidth;
	private int  openLength;
	private int  closeWidth;
	private int  closeLength;
	private int cardCopies;
	private boolean  existingPlate;
	private boolean  dataSuppliedByCustomer;
	private boolean  layOutByUs;
	private boolean  typesettingByUs;
	private JobActivityOptionDTO jobActivities;
	private List<JobPaperDTO> jobPapers;
}
