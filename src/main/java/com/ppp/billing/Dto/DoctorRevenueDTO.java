package com.ppp.billing.Dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoctorRevenueDTO {
	
		private String doctorName;
	    private long consultationCount;
	    private BigDecimal totalRevenue;
	    private BigDecimal doctorPercentage;
	    private BigDecimal amountToPay;
	    
	    public DoctorRevenueDTO(String doctorName, long consultationCount, BigDecimal totalRevenue, BigDecimal doctorPercentage) {
	        this.doctorName = doctorName;
	        this.consultationCount = consultationCount;
	        this.totalRevenue = totalRevenue;
	        this.doctorPercentage = doctorPercentage;
	        this.amountToPay = BigDecimal.ZERO; // Placeholder, to be calculated later
	    }
	    
	    public void calculateAmountToPay() {
	        if (totalRevenue != null && doctorPercentage != null) {
	            this.amountToPay = totalRevenue.multiply(doctorPercentage).divide(BigDecimal.valueOf(100));
	        }
	    }

}
