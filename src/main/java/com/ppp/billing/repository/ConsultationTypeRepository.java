package com.ppp.billing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppp.billing.Dto.ConsultationSubtypeDto;
import com.ppp.billing.model.ConsultationSubtype;
import com.ppp.billing.model.ConsultationType;

public interface ConsultationTypeRepository extends JpaRepository<ConsultationType, Long> {

	List<ConsultationSubtype> findBysubtypesId(Long id);
	
	
}
