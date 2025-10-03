package com.ppp.billing.service;

import java.time.LocalDate;
import java.util.List;

import com.ppp.billing.Dto.DoctorActivityDTO;
import com.ppp.billing.Dto.SubtypeStatsDTO;
import com.ppp.billing.model.Visit;

public interface VisitService {

	 Visit saveVisit(Visit visit);
	    Visit getVisitById(Long id);
	    List<Visit> getAllVisits();
	    List<Visit> getVisitsByPatient(Long patientId);
	    void deleteVisit(Long id);
		List<SubtypeStatsDTO> getDailySubtypeStats(LocalDate date);
		List<SubtypeStatsDTO> getWeeklySubtypeStats(LocalDate date);
		List<SubtypeStatsDTO> getMonthlySubtypeStats(LocalDate date);
		void debugVisits(LocalDate start, LocalDate end);
		List<DoctorActivityDTO> getMostActiveDoctors(LocalDate startDate, LocalDate endDate);
		
}
