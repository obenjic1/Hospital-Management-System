package com.ppp.billing.Dto;

import lombok.Data;

@Data
public class DoctorActivityDTO {
    private Long doctorId;
    private String doctorName;
    private Long visitCount;

    public DoctorActivityDTO(Long doctorId, String doctorName, Long visitCount) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.visitCount = visitCount;
    }

    // getters & setters
}