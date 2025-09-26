package com.ppp.billing.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private Long id;

    private String name;
    private int age;
    private String gender;
    private String contact;
    private String emmergencyContact; 
    private String emmergenceName;
    private String occupation;
    private String maritalStatus;
    private String residence;
   
}
