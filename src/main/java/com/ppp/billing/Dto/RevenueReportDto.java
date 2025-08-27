package com.ppp.billing.Dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RevenueReportDto {

	
	
	private BigDecimal serviceRevenue;
    private BigDecimal pharmacyRevenue;
    private BigDecimal totalRevenue;
    
    	public RevenueReportDto(BigDecimal serviceRevenue, BigDecimal pharmacyRevenue) {
        this.serviceRevenue = serviceRevenue;
        this.pharmacyRevenue = pharmacyRevenue;
        this.totalRevenue = serviceRevenue.add(pharmacyRevenue);
    }
}
