package com.ppp.billing.Dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SubserviceDTO {
	private Long id;
    private String name;
    private BigDecimal price;
}
