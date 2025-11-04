package com.ppp.billing.Dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactureItemDTO {
    private String factureRef;
    private String customer;
    private String reason;
    private String name;
    private BigDecimal price;
    private int quantity;
    private BigDecimal total;
    private BigDecimal amountPaid;
    private double discount;
    private String status;
    
    
}
