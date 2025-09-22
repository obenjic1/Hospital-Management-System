package com.ppp.billing.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consultation {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @OneToOne
	    @JoinColumn(name = "appointment_id")
	    private Appointment appointment;

	  
	    
	    private String type;
	    private String referenceNumber;
	    
		private String UnreegisteredPatientName;
		
		private String PhoneNumber;
		
		private String paymentType;
		
		private BigDecimal amountPaid;

	    @ManyToOne
	    @JoinColumn(name = "patient_id", nullable = true)
	    private Patient patient;

	    @ManyToOne
	    @JoinColumn(name = "doctor_id", nullable = false)
	    private Staff doctor; 

	    private String notes;

	    private LocalDateTime consultationDate = LocalDateTime.now();
	    @ManyToOne
	    @JoinColumn(name = "consultation_subtype_id", nullable = false)
	    private ConsultationSubtype consultationSubtype;

	   

}
