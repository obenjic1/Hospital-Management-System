package com.ppp.billing.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MonthlyServiceStat {
	
	private String serviceName;
    private long timesUsed;
    private double totalRevenue;

    public MonthlyServiceStat(String serviceName, long timesUsed, double totalRevenue) {
        this.serviceName = serviceName;
        this.timesUsed = timesUsed;
        this.totalRevenue = totalRevenue;
    }


}
