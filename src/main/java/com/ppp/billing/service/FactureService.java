package com.ppp.billing.service;

import java.time.LocalDate;
import java.util.List;

import com.ppp.billing.Dto.DailySaleDTO;
import com.ppp.billing.model.Facture;

public interface FactureService {
		Facture saveFacture(Facture facture);
	    Facture getFactureById(Long id);
	    Facture getFactureByVisit(Long visitId);
	    List<Facture> getAllFactures();
	    void deleteFacture(Long id);
		List<Facture> findByVisit_Patient_IdOrderByIdDesc(Long id);
		List<Facture> findByVisit_Patient_NameContainingIgnoreCaseOrderByIdDesc(String patientName);
		List<Facture> findAllByOrderByIdDesc();
		List<Facture> findByCreatedAtBetweenOrderByIdDesc(LocalDate localDateTime, LocalDate localDateTime2);
		List<DailySaleDTO> getTodaysSales();
}
