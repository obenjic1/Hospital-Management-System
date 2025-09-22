package com.ppp.billing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.ConsultationSubtype;
import com.ppp.billing.model.dto.ConsultationSubtypeRepository;

@Service
public class ConsultationSubtypeService {
    @Autowired
    private ConsultationSubtypeRepository consultationSubtypeRepository;

    public List<ConsultationSubtype> findByConsultationTypeId(Long typeId) {
        return consultationSubtypeRepository.findByConsultationTypeId(typeId);
    }
    
    public ConsultationSubtype save(ConsultationSubtype subtype) {
        return consultationSubtypeRepository.save(subtype);
    }
}
