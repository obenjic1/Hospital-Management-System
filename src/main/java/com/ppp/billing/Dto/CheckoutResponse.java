package com.ppp.billing.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutResponse {
	
	
	private long id;
	private String cashierName;
	private String CustomerName;
	private String Total;
    private String receiptNumber;
    private String status;



}
