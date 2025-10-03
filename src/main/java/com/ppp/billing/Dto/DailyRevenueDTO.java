package com.ppp.billing.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
@Data
public class DailyRevenueDTO {
	
	private LocalDate date;
    private BigDecimal total;

    public DailyRevenueDTO(LocalDate date, BigDecimal total) {
        this.date = date;
        this.total = total != null ? total : BigDecimal.ZERO; 
    }


   

}
