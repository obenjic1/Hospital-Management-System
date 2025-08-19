package com.ppp.billing.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="pharmacy_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyItem {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    private Medicine medicine;  // The medicine this pharmacy item refers to

	    private int quantity;       // Current stock quantity in pharmacy

	    private LocalDate expiryDate;  // Expiry date of this batch (important for pharmacies)

	    private String batchNumber;     // Batch or lot number of medicine (optional but useful)

	 

  
}
