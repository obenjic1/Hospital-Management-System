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
   private int volume;
   private int paperTypeId;
   private int contentTypeId;
   private int paperUnitPrice;
	private double  paperSizeLength;
	private double  paperSizeWidth;
   private List<JobColorCombinationDTO> jobColorCombinations;
}
