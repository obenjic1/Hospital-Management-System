package com.ppp.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppp.billing.model.PaperFormat;


@Repository
public interface PaperFormatRepository extends JpaRepository<PaperFormat, Long> {
	
	PaperFormat findByName(String name);

}
