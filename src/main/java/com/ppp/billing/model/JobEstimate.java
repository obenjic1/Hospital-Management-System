package com.ppp.billing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "job_estimate")
public class JobEstimate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "reference")
    private  String reference;
    
    @Column(name = "tva", columnDefinition = "int default 0")
    private  boolean tva = Boolean.FALSE;
    
    @Column(name = "is_invoiced")
    private  boolean invoiced = Boolean.FALSE;
    
    @Column(name = "ir_tax")
    private float irTax;

    @Column(name = "commission", columnDefinition = "float default 0")
    private double commission;
    
    @Column(name = "discount_value", columnDefinition = "float default 0")
	 private double discountValue;
    
    @Column(name = "advance_percentage", columnDefinition = "float default 0")
    private float advancePercentage;

    @Column(name="expected_delivery_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

  
  

}
