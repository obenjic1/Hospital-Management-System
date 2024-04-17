package com.ppp.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppp.billing.model.PaperType;


@Repository
public interface PaperTypeRepository extends JpaRepository<PaperType, Long>{
	
		PaperType findByName(String name);

}
