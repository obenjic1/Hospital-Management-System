package com.ppp.billing.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
	    private Long medicineId;
	    private String medicineName;
	    private int quantity;
	    private BigDecimal unitPrice;
	    
	    public BigDecimal getTotalPrice() {
	        if (unitPrice == null) {
	            return BigDecimal.ZERO;
	        }
	        return unitPrice.multiply(BigDecimal.valueOf(quantity));
	    }
}
