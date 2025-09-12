package com.ppp.billing.Dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoctorRevenueSummary {
	
	 private List<DoctorRevenueDTO> doctorStats;
	    private BigDecimal totalRevenue;
	    private BigDecimal totalDoctorPayout;
	    private BigDecimal profit;

}
