package com.ppp.billing.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentItemDto {
	
	
	private long patientId;
	private String UnreegisteredPatien;
	private String paymentMethod;
	
	
	

}
