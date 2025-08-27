package com.ppp.billing.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.security.core.context.SecurityContextHolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"patient", "service"})
public class PaymentItem {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = true)    private Patient patient;
    private String unregisteredPatientName;

    
    @ManyToMany
    @JoinTable(
            name = "payment_service_items",
            joinColumns = @JoinColumn(name = "payment_id"),
            inverseJoinColumns = @JoinColumn(name = "service_item_id")
        )
        private List<ServiceItem> serviceItems;


    private Double amount;
    private String paymentMethod; 
    private String status;         
    private LocalDateTime paymentDate = LocalDateTime.now();
    private Double ballance;
    private Double amountPaid;
    private String ReferenceNumber;
    
    @OneToMany(mappedBy = "paymentItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tracking> tracking = new ArrayList<>();

    // helper method to add history
    public void addTracking(String action, String description) {
  		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
  	   
        Tracking t = new Tracking();
        t.setAction(action);
        t.setDescription(description);
        t.setPerformedBy(userName);
        t.setCreationDate(LocalDateTime.now());
        t.setPaymentItem(this);
        this.tracking.add(t);
    }

}
