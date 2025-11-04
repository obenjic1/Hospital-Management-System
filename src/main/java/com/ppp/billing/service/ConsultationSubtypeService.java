package com.ppp.billing.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.ConsultationSubtype;
import com.ppp.billing.model.Visit;
import com.ppp.billing.repository.ConsultationSubTypeRepository;
import com.ppp.billing.repository.ConsultationTypeRepository;

@Service
public class ConsultationSubtypeService {
    @Autowired
    private ConsultationSubTypeRepository subTypeRepository;
    
    @Autowired
    private ConsultationTypeRepository consultationTypeRepository;

    public List<ConsultationSubtype> findByConsultationTypeId(Long typeId) {
        return subTypeRepository.findAllByConsultationTypeId(typeId);
    }
    
    public ConsultationSubtype save(ConsultationSubtype subtype) {
        return subTypeRepository.save(subtype);
    }
    
    public ConsultationSubtype findById(Long id) {
    	return subTypeRepository.findById(id).orElse(null);
    }

	public ConsultationSubtype edit(Long id, ConsultationSubtype updatedSubtype) {
		ConsultationSubtype subtype = findById(id);
		subtype.setName(updatedSubtype.getName());
		subtype.setPrice(updatedSubtype.getPrice());
		subTypeRepository.save(subtype);
		return subtype;
		
	}

	@Transactional
	public void delete(Long id) {
		 ConsultationSubtype subtype = findById(id);

			    // Remove associations with visits
			    for (Visit visit : subtype.getVisits()) {
			        visit.getSubtypes().remove(subtype);
			    }
			    subtype.getVisits().clear();

			    subTypeRepository.delete(subtype);
		
		
		
		
		
		
	}
}
