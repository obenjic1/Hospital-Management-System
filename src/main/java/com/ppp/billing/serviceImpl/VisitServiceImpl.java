package com.ppp.billing.serviceImpl;

import java.util.List;

import com.ppp.billing.model.Visit;
import com.ppp.billing.repository.VisitRepository;
import com.ppp.billing.service.VisitService;

public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;

    public VisitServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Visit saveVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public Visit getVisitById(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    @Override
    public List<Visit> getVisitsByPatient(Long patientId) {
        return visitRepository.findByPatientId(patientId);
    }

    @Override
    public void deleteVisit(Long id) {
        visitRepository.deleteById(id);
    }
}