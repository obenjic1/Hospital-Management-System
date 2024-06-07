package com.ppp.billing.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstimateDTO {
	private int quantity;
	private double unitPrice;
	private double totalPrice;
	private String reference;

}
