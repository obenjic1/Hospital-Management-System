package com.ppp.billing.Dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
	    private Long id;      
	    private String name;   
	    private BigDecimal price;
	    private int qty;

}
