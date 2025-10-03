package com.ppp.billing.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CartItem {

	@JsonProperty("id")  
	private long medicineId;
	@JsonProperty("qty")
	private int quantity;
	private String unitType;
	private String name;
	private BigDecimal price;
	
}
