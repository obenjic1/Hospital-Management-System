package com.ppp.billing.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ppp.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "facture_id")
    private Facture facture;

    private BigDecimal amountPaid;
    private LocalDateTime paymentDate = LocalDateTime.now();
    private String paymentMethod; 
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;
    @Column(name = "receipt_path")
    private String receiptPath;   // absolute path on disk

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "received_by")  
    private User receivedBy;
    
    
    
    private String reference;
    public boolean isCash()    { return method == PaymentMethod.CASH; }
    public boolean isMTN()    { return method == PaymentMethod.MTN_MOBILE_MONEY; }
    public boolean isOrange()  { return method == PaymentMethod.ORANGE_MONEY; }
    
    
}
