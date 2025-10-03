package com.ppp.billing.Dto;

import java.math.BigDecimal;
import java.util.List;

import com.ppp.billing.model.CartItem;

import lombok.Data;

@Data
public class CheckoutRequest {
	 private String customerName;
     private List<CartItem> cartItems;
     private String paymentMethod;
}
