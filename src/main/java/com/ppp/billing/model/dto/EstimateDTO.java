package com.ppp.billing.model.dto;

import java.util.Date;
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
	private Date createdDate;
    private float advancePercentage;
    private boolean tva;
    private float irTax;


}
