package com.ppp.billing.Dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DoctorConsultationDTO {
	 private String doctorName;
	    private String consultationName;
	    private BigDecimal totalGenerated;
	    private BigDecimal doctorPay;
	    private BigDecimal hospitalProfit;

	    public DoctorConsultationDTO(String doctorName, String consultationName, BigDecimal totalGenerated,
	                                 BigDecimal doctorPay, BigDecimal hospitalProfit) {
	        this.doctorName = doctorName;
	        this.consultationName = consultationName;
	        this.totalGenerated = totalGenerated;
	        this.doctorPay = doctorPay;
	        this.hospitalProfit = hospitalProfit;
	    }

		

}
