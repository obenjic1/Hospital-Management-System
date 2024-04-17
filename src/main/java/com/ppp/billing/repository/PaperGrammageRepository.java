package com.ppp.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppp.billing.model.PaperGrammage;

@Repository
public interface PaperGrammageRepository extends JpaRepository<PaperGrammage, Long> {
	
	PaperGrammage findByValue(String value);

}
