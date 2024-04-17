package com.ppp.billing.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrintingDTO {
	
	private String name;
	private String type;
	private int unitPrice;
	private int value;

}
