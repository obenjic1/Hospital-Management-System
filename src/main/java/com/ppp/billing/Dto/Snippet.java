package com.ppp.billing.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Snippet {
	 // 1. Patient Details
	    private String firstName;
	    private Integer age;
	    private String gender;
	    private String contact;
	    private String residence;
	    private String occupation;
	    private String maritalStatus;
	    private String emergencyName;
	    private String emergencyContact;
	
	    // 2. Visit Details
	    private Long patientId;           // Patient ID, filled if existing patient selected
	    private Long reasonId;            // Visit reason ID
	    private String reasonName;        // Visit reason name
	    private LocalDate visitDate = LocalDate.now();
	    private LocalTime visitTime = LocalTime.now();
	
	    // 3. Services (dynamic rows)
	    private List<ServiceDTO> services = new ArrayList<>();  // List to hold dynamic services (consultation, exams, etc.)
	    
	    // 4. Payment
	    private BigDecimal totalAmount;
	    private BigDecimal discount;
	    private Long doctorId;
	
	    // 5. Optional Appointment
	    private Boolean createAppointment;
	    private String appointmentReason;
	    private LocalDate appointmentDate;
	    private LocalTime appointmentTime;
	
}

