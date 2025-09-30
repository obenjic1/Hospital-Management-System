package com.ppp.billing.Dto;

import lombok.Data;

@Data
public class ServiceDTO {
    private Long serviceTypeId;
    private Double price;
    private SubserviceDTO subserviceDTO; 

    // Getters and setters
}