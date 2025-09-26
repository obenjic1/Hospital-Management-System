package com.ppp.billing.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;

    private double totalAmount;
    private double discount;
    private double netAmount;
    private String status; // PAID, UNPAID, PARTIAL

    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL)
    private List<Payment> payments;
    
    private boolean fullyPaid;
  
    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FactureItem> items = new ArrayList<>();
    
 
    private double amountPaid;     
    private double balance;        
}

