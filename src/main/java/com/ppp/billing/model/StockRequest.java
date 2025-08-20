package com.ppp.billing.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ppp.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="stock_requests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PharmacyItem pharmacyItem;

    private int requestedQuantity;

    private boolean fulfilled;

    private LocalDateTime requestDate;

    @ManyToOne
    private User requestedBy;  // The user who made the request

    @ManyToOne
    private User fulfilledBy; 
   
}
