package com.ppp.billing.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationSubtypeRepository extends JpaRepository<ConsultationSubtype, Long> {
    List<ConsultationSubtype> findByConsultationTypeId(Long consultationTypeId);
}