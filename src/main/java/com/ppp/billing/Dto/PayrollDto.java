package com.ppp.billing.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayrollDto {
	
	 private long staff;

	 private Double baseSalary;
	 private Double allowances;
	 private Double deductions;
	 private Double netSalary;
	 private Double basicSalary;

	 private String month;

}
