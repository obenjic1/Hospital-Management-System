package com.ppp.billing.model;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ServiceUsageStats {
		private String serviceName;
	    private Double unitPrice;
	    private Long timesUsed;
	    private Double totalRevenue;

	    public ServiceUsageStats(String serviceName, Double unitPrice, Long timesUsed, Double totalRevenue) {
	        this.serviceName = serviceName;
	        this.unitPrice = unitPrice;
	        this.timesUsed = timesUsed;
	        this.totalRevenue = totalRevenue;
	    }
    

}