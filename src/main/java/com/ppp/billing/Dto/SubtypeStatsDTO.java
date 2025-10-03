package com.ppp.billing.Dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SubtypeStatsDTO {
    private String subtypeName;
    private Long count;
    private BigDecimal revenue;

    public SubtypeStatsDTO(String subtypeName, Long count, BigDecimal revenue) {
        this.subtypeName = subtypeName;
        this.count = count;
        this.revenue = revenue;
    }

    // getters & setters
}
