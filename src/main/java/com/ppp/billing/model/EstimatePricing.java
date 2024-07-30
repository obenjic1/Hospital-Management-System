package com.ppp.billing.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name ="estimate_pricing")

public class EstimatePricing {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
		
		@Column(name = "quantity")
	    private int quantity;

	    @Column(name = "unit_price")
	    private double unitPrice;

	    @Column(name = "total_price")
	    private double totalPrice;
	    
	    @Column(name = "invoiced")
	    private boolean invoiced = Boolean.FALSE;
	   
	    
	    @ManyToOne
		@JoinColumn(name = "job_estimate_id", referencedColumnName = "id")
		private JobEstimate jobEstimate;
	    
	    @OneToMany(mappedBy = "estimatePricingid", cascade = CascadeType.PERSIST)
	    private List<Invoice> invoices;

	

}

