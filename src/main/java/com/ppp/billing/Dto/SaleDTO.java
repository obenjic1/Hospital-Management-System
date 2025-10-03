package com.ppp.billing.Dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class SaleDTO {

	private Long customerId;          // optional
    private BigDecimal totalAmount;
    private BigDecimal discount;
    private BigDecimal netAmount;
    private BigDecimal amountPaid;
    private BigDecimal balance;
    private String paymentMethod;     // CASH, MOBILE_MONEY, CARD
    private List<SaleItemDTO> items;
}
