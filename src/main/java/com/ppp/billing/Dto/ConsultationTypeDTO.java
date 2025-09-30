package com.ppp.billing.Dto;

import java.util.List;

import com.ppp.billing.model.ConsultationSubtype;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsultationTypeDTO {

	
	private Long id;
    private String name;
    private List<ConsultationSubtypeDTO> subtypes;
    
    
    public ConsultationTypeDTO(Long id, String name, List<ConsultationSubtypeDTO> subtypes) {
        this.id = id;
        this.name = name;
        this.subtypes = subtypes;
        }
    
    
    
}
