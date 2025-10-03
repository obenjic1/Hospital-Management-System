package com.ppp.billing.Dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SaleItemDTO {

	private Long medicineId;
    private String medicineName;
    private int qtyPackets;
    private int qtyUnits;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
}
