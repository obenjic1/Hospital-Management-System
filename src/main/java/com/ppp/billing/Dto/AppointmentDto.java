package com.ppp.billing.Dto;


import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
	private Long patientId;
	private Long doctor_id;
	private String reason;
    private String status; 
    private LocalDate appoitmentDate;


}
