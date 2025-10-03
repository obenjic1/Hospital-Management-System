package com.ppp.billing.Dto;

import java.math.BigDecimal;


import lombok.Data;

@Data
public class DailySaleDTO {
    private String factureId;
    private String patientName;   // visit.patient or facture.customerName
    private String reasonName;    // visit.reasonName (if visit != null)
    private BigDecimal netAmount;
    private BigDecimal paidAmount;
    private double discount;
    private String paymentStatus;
    
    

    public DailySaleDTO(String r, String patientName, String reasonName,
                        BigDecimal netAmount, BigDecimal paidAmount,
                        double discount, String paymentStatus) {
        this.factureId = r;
        this.patientName = patientName;
        this.reasonName = reasonName;
        this.netAmount = netAmount;
        this.paidAmount = paidAmount;
        this.discount = discount;
        this.paymentStatus = paymentStatus;}
    
}
