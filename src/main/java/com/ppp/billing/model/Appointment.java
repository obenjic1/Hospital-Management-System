package com.ppp.billing.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
	
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "patient_id", nullable = false)
	    private Patient patient;

	    @ManyToOne
	    @JoinColumn(name = "doctor_id", nullable = false)
	    private Staff doctor; 
	    
	    private LocalDate appointmentDate;

	    private AppointmentStatus status; 
	    private String reason;


	    
	    
//	    public void setAppointmentDate(LocalDate localDate) {
//	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//	        this.appointmentDate = localDate;
//	    }

}
