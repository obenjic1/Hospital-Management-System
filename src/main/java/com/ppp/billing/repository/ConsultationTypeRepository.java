package com.ppp.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.ConsultationSubtype;
import com.ppp.billing.model.ConsultationType;

public interface ConsultationTypeRepository extends JpaRepository<ConsultationType, Long> {

	List<ConsultationSubtype> findBysubtypesId(Long id);
}
