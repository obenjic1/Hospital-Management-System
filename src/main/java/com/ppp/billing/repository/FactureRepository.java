package com.ppp.billing.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.Facture;

public interface FactureRepository  extends JpaRepository<Facture, Long>{
    Facture findByVisitId(Long visitId);
    List<Facture> findByVisit_Patient_IdOrderByIdDesc(Long id);
    List<Facture> findByCreatedDateBetweenOrderByIdDesc(LocalDate start, LocalDate end);
    List<Facture> findAllByOrderByIdDesc();
	List<Facture> findByVisit_Patient_NameContainingIgnoreCaseOrderByIdDesc(String patientName);

}
