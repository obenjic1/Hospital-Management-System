package com.ppp.billing.serviceImpl;

import java.util.List;

import com.ppp.billing.model.Facture;
import com.ppp.billing.repository.FactureRepository;
import com.ppp.billing.service.FactureService;

public class FactureServiceImpl implements FactureService {

    private final FactureRepository factureRepository;

    public FactureServiceImpl(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    @Override
    public Facture saveFacture(Facture facture) {
        return factureRepository.save(facture);
    }

    @Override
    public Facture getFactureById(Long id) {
        return factureRepository.findById(id).orElse(null);
    }

    @Override
    public Facture getFactureByVisit(Long visitId) {
        return factureRepository.findByVisitId(visitId);
    }

    @Override
    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    @Override
    public void deleteFacture(Long id) {
        factureRepository.deleteById(id);
    }

}
