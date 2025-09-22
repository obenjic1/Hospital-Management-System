package com.ppp.billing.model.dto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.ConsultationSubtype;

public interface ConsultationSubtypeRepository extends JpaRepository<ConsultationSubtype, Long> {
    List<ConsultationSubtype> findByConsultationTypeId(Long consultationTypeId);
}