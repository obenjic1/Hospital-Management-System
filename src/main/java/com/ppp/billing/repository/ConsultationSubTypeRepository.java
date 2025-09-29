package com.ppp.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ppp.billing.model.ConsultationSubtype;


@Repository
public interface ConsultationSubTypeRepository extends JpaRepository<ConsultationSubtype, Long>{
    List<ConsultationSubtype> findByConsultationTypeId(Long consultationTypeId);
	List<ConsultationSubtype> findAllByConsultationTypeId(Long typeId);
	

}
