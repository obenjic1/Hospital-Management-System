package com.ppp.billing.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ppp.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tracking")
public class Tracking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

    private String action;        // CREATED, UPDATED, TRANSFERRED, DELETED
	private String performedBy;   // username / staffId

	@Column(name = "description")
	private String description;
	
	@Column(name="creation_date")
	private LocalDateTime creationDate;
	

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	 @ManyToOne
	  @JoinColumn(name = "medicine_id")
	  private Medicine medicine;
	 
	 @ManyToOne
	  @JoinColumn(name = "paymentItem_id")
	  private PaymentItem paymentItem;
	 
	 @ManyToOne
	  @JoinColumn(name = "patient_id")
	  private Patient patient;
	 
	 @ManyToOne
	  @JoinColumn(name = "service_item_id")
	  private ServiceItem serviceItem;
	
	

}
