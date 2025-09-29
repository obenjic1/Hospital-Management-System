package com.ppp.billing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.Dto.ConsultationSubtypeDTO;
import com.ppp.billing.model.ConsultationSubtype;
import com.ppp.billing.repository.ConsultationSubTypeRepository;

@Service
public class ConsultationSubtypeService {
    @Autowired
    private ConsultationSubTypeRepository subTypeRepository;

    public List<ConsultationSubtype> findByConsultationTypeId(Long typeId) {
        return subTypeRepository.findAllByConsultationTypeId(typeId);
    }
    
    public ConsultationSubtype save(ConsultationSubtype subtype) {
        return subTypeRepository.save(subtype);
    }
    
    public ConsultationSubtype findById(Long id) {
    	return subTypeRepository.findById(id).orElse(null);
    }
}
