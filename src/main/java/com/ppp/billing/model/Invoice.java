package com.ppp.billing.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@Column(name = "ir_tax_value", columnDefinition = "float default 0")
	private double irTaxValue;
	
	@Column(name = "tva_value", columnDefinition = "float default 0")
	private double tvaValue;
	
	@Column(name = "creation_date")
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	
	@Column(name = "net_payable",columnDefinition = "float default 0")
	private double netPayable;
	
	@ManyToOne
	@JoinColumn(name = "estimate_pricing_id", referencedColumnName = "id")
	private EstimatePricing estimatePricingid;

}
