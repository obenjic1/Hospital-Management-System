package com.ppp.billing.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.context.SecurityContextHolder;

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

	    private LocalDate saleDate = LocalDate.now();

	    private BigDecimal total;

	    @ManyToOne
	    @JoinColumn(name = "pharmacist_id")
	    private User pharmacist;  // or Staff entity

	    private String paymentMethod;

	    @Column(name = "receipt_path")
	    private String receiptPath; 
	    
	    private String receiptNumber; 

	    private String customerName; 
	    private String customerContact;
	    
	    
	    private BigDecimal totalAmount;
	    private double discount;
	    private BigDecimal netAmount;
	    private BigDecimal amountPaid;
	    private double balance;

	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "facture_id")
	    private Facture facture;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "payment_id")
	    private Payment payment;   
	    
	    
	    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
	    @JsonProperty("items")
	    
	    private List<SaleItem> items = new ArrayList<>();

	   
	    // convenience method
	    public void addItem(SaleItem item) {
	        items.add(item);
	        item.setSale(this);
	    }
	    public void addTracking(String action, String description) {
	        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
	        Tracking t = new Tracking();
	        t.setAction(action);
	        t.setDescription(description);
	        t.setPerformedBy(userName);
	        t.setCreationDate(LocalDateTime.now());
	    }
}
