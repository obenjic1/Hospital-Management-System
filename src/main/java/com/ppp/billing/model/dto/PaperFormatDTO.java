package com.ppp.billing.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperFormatDTO {
	
	private String name;
	private String length;
	private String width;

}
