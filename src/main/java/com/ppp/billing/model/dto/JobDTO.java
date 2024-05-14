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
	private int totalContentSignature;
	private int totalCoverSignature;
	private boolean existingPlate;
	private boolean dataSuppliedByCustomer;
	private boolean typesettingByUs;
	private boolean layOutByUs;
	private Date readytoPrintDate;
	private Date expectedDeliveryDate;
	private Date creationDate;
	private List<JobActivityDTO> jobActivitiesDTO;
	private List<JobTrackingDTO> jobTrackingsDTO;
	private List<JobPaperDTO> jobPapersDTO;
	private CustomerDTO customerDTO;
	private BindingTypeDTO bindingTypeDTO;
	private JobStatusDTO jobStatusDTO;
	private JobTypeDTO jobTypeDTO;

}
