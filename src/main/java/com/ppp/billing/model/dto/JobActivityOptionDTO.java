package com.ppp.billing.model.dto;

import java.util.List;

import com.ppp.billing.model.JobActivity;
import com.ppp.billing.model.JobOperationOption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobActivityOptionDTO {
	
	private String name;
	private JobOperationOptionDTO  jobOperationOptionDTO;
	private List<JobActivityDTO> jobActivitiesDTO;

}
