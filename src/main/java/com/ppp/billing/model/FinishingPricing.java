package com.ppp.billing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "finishing_pricing")
public class FinishingPricing {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	
	@Column
	private String type;
	
	@Column(name="unit_price")
	private int unitPrice;
	
	@Column
	private int value;
	
	@ManyToOne
	@JoinColumn(name = "job_id", referencedColumnName = "id")
	private Job job;

}
