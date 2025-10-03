package com.ppp.billing.Dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class PharmacySaleDTO {

	 private Long patientId;          // optional
	    private String customerContact;
	    private BigDecimal totalAmount;
	    private BigDecimal discount;
	    private BigDecimal netAmount;
	    private BigDecimal amountPaid;
	    private BigDecimal balance;
	    private String paymentMethod;  
	    private List<SaleItemDTO> items; 
	    
	    
	    
}
