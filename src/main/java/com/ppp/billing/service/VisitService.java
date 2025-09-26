package com.ppp.billing.service;

import java.util.List;

import com.ppp.billing.model.Visit;

public interface VisitService {

	 Visit saveVisit(Visit visit);
	    Visit getVisitById(Long id);
	    List<Visit> getAllVisits();
	    List<Visit> getVisitsByPatient(Long patientId);
	    void deleteVisit(Long id);
}
