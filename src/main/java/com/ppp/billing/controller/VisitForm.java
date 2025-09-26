package com.ppp.billing.controller;

import java.util.List;

import com.ppp.billing.model.Visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitForm {
	
		Long patientId;
	     private String name;
	    private int age;
	    private String gender;
	    private String contact;
	    private String emmergencyContact; 
	    private String emmergenceName;
	    private String occupation;
	    private String maritalStatus;
	    private String residence;
	    private List<Visit> visits;
	

}
