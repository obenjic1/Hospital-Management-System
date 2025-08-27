package com.ppp.billing.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayrollSummaryDto {
	
    private String department;
    private Integer month;
    private Double total;

   


}
