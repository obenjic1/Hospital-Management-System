package com.ppp.billing.model.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
	
	private String title;
	private String referenceNumber;
	private String description;
	private int contentVolume;
	private int coverVolume;
	private int contentSignature;
	private int coverSignature;
	private boolean existingPlate;
	private Date readytoPrintDate;
	private Date expectedDeliveryDate;
	private Date creationDate;
	private List<String> ids;


}
