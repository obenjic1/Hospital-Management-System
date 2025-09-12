package com.ppp.billing.Dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDto {

	
	private String type;
	private String unregisteredPatientName;
    private Long PatientId;
	private String PhoneNumber;
	private Long DoctorId;
	private String paymentType;
	private BigDecimal AmountPaid;


}
