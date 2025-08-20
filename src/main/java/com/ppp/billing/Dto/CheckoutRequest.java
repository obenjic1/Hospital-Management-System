package com.ppp.billing.Dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequest {
	
	    private List<CartItemDto> cartItems;
	    private String customerName;
	    private String paymentMethod;


}
