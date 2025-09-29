package com.ppp.billing.Dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ConsultationSubtypeDTO {
	 private Long   serviceTypeId;
	 private BigDecimal price;
}
