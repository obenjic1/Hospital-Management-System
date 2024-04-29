package com.ppp.billing.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPaperDTO {

	private int grammage;
	private String openLength;
	private String closeLength;
	private String openWidth;
	private String closeWidth;
	private int volume;
	private List<String> jobColorCombinations;
}
