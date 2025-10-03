package com.ppp.billing.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Visit {

	
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    private Patient patient;
	    
	    @ManyToOne
	    @JoinColumn(name = "staff_id")
	    private Staff attendingStaff;
	    private LocalDate visitDate;
	    private LocalTime visitTime;
	    
	 
	    
	    @OneToOne(mappedBy = "visit", cascade = CascadeType.ALL)
	    private Facture facture;
	    
	    @ManyToOne(fetch = FetchType.LAZY)  
	    @JoinColumn(name = "consultation_type_id") 
	    private ConsultationType consultationType;
	    
	    
	    @OneToOne
	    @JoinColumn(name = "appointment_id")
	    private Appointment appointment;
	    
	    @ManyToMany
	    @JoinTable(
	        name = "visit_subtypes",
	        joinColumns = @JoinColumn(name = "visit_id"),
	        inverseJoinColumns = @JoinColumn(name = "subtype_id")
	    )
	    private List<ConsultationSubtype> subtypes = new ArrayList<>();
    
   
}
