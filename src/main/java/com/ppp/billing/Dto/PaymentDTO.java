package com.ppp.billing.Dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
 private Long factureId;
 private BigDecimal amountPaid;
 private String paymentMethod;


 
}
