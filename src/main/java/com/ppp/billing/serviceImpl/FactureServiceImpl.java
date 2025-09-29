package com.ppp.billing.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.Facture;
import com.ppp.billing.repository.FactureRepository;
import com.ppp.billing.service.FactureService;

@Service

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

	@Override
	public List<Facture> findByVisit_Patient_IdOrderByIdDesc(Long id) {
		
		return factureRepository.findByVisit_Patient_IdOrderByIdDesc(id);
	}

	@Override
	public List<Facture> findByVisit_Patient_NameContainingIgnoreCaseOrderByIdDesc(String patientName) {
		
		return factureRepository.findByVisit_Patient_NameContainingIgnoreCaseOrderByIdDesc( patientName);
	}

	@Override
	public List<Facture> findAllByOrderByIdDesc() {
		
		return factureRepository.findAllByOrderByIdDesc();
	}

	@Override
	public List<Facture> findByCreatedAtBetweenOrderByIdDesc(LocalDate atStartOfDay, LocalDate atStartOfDay2) {
		return factureRepository.findByCreatedDateBetweenOrderByIdDesc( atStartOfDay,  atStartOfDay2);
	}

}
