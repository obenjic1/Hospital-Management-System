package com.ppp.billing.service;

import java.util.List;

import com.ppp.billing.model.Facture;

public interface FactureService {
		Facture saveFacture(Facture facture);
	    Facture getFactureById(Long id);
	    Facture getFactureByVisit(Long visitId);
	    List<Facture> getAllFactures();
	    void deleteFacture(Long id);
}
