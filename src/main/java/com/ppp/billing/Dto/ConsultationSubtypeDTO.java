package com.ppp.billing.Dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationSubtypeDTO {
	 private Long   id;
	 private BigDecimal price;
	 private String name;
	 public ConsultationSubtypeDTO(Long id, String name, BigDecimal bigDecimal) {
	        this.id = id;
	        this.name = name;
	        this.price = bigDecimal;
	    }
}
