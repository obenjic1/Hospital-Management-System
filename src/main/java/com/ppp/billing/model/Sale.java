package com.ppp.billing.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ppp.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="sales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "medicine_id")
	    private Medicine medicine;

	    private int quantity;

	    private LocalDateTime saleDate;

	    private BigDecimal total;

	    @ManyToOne
	    @JoinColumn(name = "pharmacist_id")
	    private User pharmacist;  // or Staff entity

	    private String paymentMethod;

	    private String receiptNumber; 

	    private String customerName;   
	    

	    
	    
	    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
	    @JsonProperty("items")
	    
	    private List<SaleItem> items = new ArrayList<>();

	    // convenience method
	    public void addItem(SaleItem item) {
	        items.add(item);
	        item.setSale(this);
	    }
}
