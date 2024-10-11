package com.ppp.billing.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "invoice")
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "reference_number")
	private String referenceNumber;

	@Column(name = "vat_percentage", columnDefinition = "float default 0")
	private double vatPercentage;

	@Column(name = "ir_tax_percentage", columnDefinition = "float default 0")
	private double irTaxPercentage;

	 @Column(name = "discount_percentage", columnDefinition = "float default 0")
	 private double discountPercentage;

	@Column(name = "creation_date")
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	
	@Transient
	private double netPayable;
	
	@ManyToOne
	@JoinColumn(name = "estimate_pricing_id", referencedColumnName = "id")
	private EstimatePricing estimatePricing;
	
	
	public double getNetPayable() {
		double totalPrice = estimatePricing.getTotalPrice();
		return totalPrice-(totalPrice*discountPercentage)/100+(totalPrice*vatPercentage)/100+(totalPrice*irTaxPercentage)/100;
	}
	
}
