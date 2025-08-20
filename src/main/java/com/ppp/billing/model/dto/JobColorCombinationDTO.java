package com.ppp.billing.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobColorCombinationDTO {
	
	private int frontColorNumber; 
	private int backColorNumber; 
	private double signatureNumber; 
	private int printTypeId;
	private String printingMachineId;
	
}
