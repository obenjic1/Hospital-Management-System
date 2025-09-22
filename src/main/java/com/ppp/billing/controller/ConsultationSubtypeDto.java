package com.ppp.billing.controller;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationSubtypeDto {

	 private Long id;
	 private String name;
	 private BigDecimal price;
}
