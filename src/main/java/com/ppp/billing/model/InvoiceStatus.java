package com.ppp.billing.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoice_status")
@Entity
public class InvoiceStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", length = 255)
	private String description;
	
	@OneToMany(mappedBy="invoiceStatus")
	private List<Invoice> invoices;
	
}
