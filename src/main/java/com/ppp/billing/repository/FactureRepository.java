package com.ppp.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.Facture;

public interface FactureRepository  extends JpaRepository<Facture, Long>{
    Facture findByVisitId(Long visitId);

}
