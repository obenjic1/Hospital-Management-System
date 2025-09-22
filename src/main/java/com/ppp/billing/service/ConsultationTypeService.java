package com.ppp.billing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.ConsultationType;
import com.ppp.billing.repository.ConsultationTypeRepository;

@Service
public class ConsultationTypeService {
    @Autowired
    private ConsultationTypeRepository consultationTypeRepository;

    public List<ConsultationType> findAll() {
        return consultationTypeRepository.findAll();
    }
    
   

    public ConsultationType save(ConsultationType type) {
        return consultationTypeRepository.save(type);
    }
    
    public List<ConsultationType> findAllWithSubtypes() {
        List<ConsultationType> types = consultationTypeRepository.findAll();
        types.forEach(t -> t.setSubtypes(consultationTypeRepository.findBysubtypesId(t.getId())));
        return types;
    }
}
