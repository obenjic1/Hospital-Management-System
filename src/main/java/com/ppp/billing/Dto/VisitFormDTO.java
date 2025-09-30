package com.ppp.billing.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class VisitFormDTO {
	
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
    private Long patientId;        
    private Long reasonId;           
    private String reasonName;      
    private LocalDate visitDate = LocalDate.now();
    
    
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime visitTime = LocalTime.now();

    // 3. Services (dynamic rows)
    private List<ConsultationSubtypeDTO> services = new ArrayList<>();  
    
    // 4. Payment
    private BigDecimal totalAmount;
    private BigDecimal netAmount;

    private long discount;
    private Long doctorId;

    // 5. Optional Appointment
    private Boolean createAppointment;
    private String appointmentReason;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
}
