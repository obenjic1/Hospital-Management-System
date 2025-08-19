package com.ppp.billing.Dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyStats {
	
	private long totalMedicines;
    private long totalQuantity;
    private BigDecimal totalValue;
    private long expiringSoon;
    private long lowStock;


}
