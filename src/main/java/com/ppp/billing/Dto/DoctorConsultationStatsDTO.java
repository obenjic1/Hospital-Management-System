package com.ppp.billing.Dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DoctorConsultationStatsDTO {
    private Long doctorId;
    private String doctorName;
    private String consultationName;
    private long consultationCount;
    private BigDecimal totalAmount;
    private BigDecimal doctorPay;
    private BigDecimal hospitalProfit;

    public DoctorConsultationStatsDTO(Long doctorId, String doctorName, String consultationName,
                                      long consultationCount, BigDecimal totalAmount, BigDecimal doctorPay,
                                      BigDecimal hospitalProfit) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.consultationName = consultationName;
        this.consultationCount = consultationCount;
        this.totalAmount = totalAmount;
        this.doctorPay = doctorPay;
        this.hospitalProfit = hospitalProfit;
    }

}
