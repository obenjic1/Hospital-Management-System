package com.ppp.billing.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.Medicine;
import com.ppp.billing.model.Patient;
import com.ppp.billing.model.RefrenceNumberGenerator;
import com.ppp.billing.repository.ActivityLogRepository;
import com.ppp.billing.model.Medicine.Location;
import com.ppp.billing.repository.PatientRepository;

@Service
public class PatientService {

    private final ActivityLogRepository activityLogRepository;

	
	@Autowired
    private PatientRepository repo;

    PatientService(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    public Patient createPatient(Patient newPatient) {
    	Patient patient = new Patient();
    	patient.setAge(newPatient.getAge());
    	patient.setContact(newPatient.getContact());
    	patient.setGender(newPatient.getGender());
    	patient.setName(newPatient.getName());
    	patient.setEmmergenceName(newPatient.getEmmergenceName());
    	patient.setEmmergencyContact(newPatient.getEmmergencyContact());
    	patient.setMaritalStatus(newPatient.getMaritalStatus());  
    	patient.setOccupation(newPatient.getOccupation());
    	patient.setResidence(newPatient.getResidence());
    	patient.setReferenceNumber(RefrenceNumberGenerator.nextPatientRef());
    	patient.addTracking("CREATE", "Added this patient");
        return repo.save(patient);
    }

    public List<Patient> getAllPatients() {
    	List<Patient> patient =repo.findAll();
    	//patient.sort(Comparator.comparing(Patient::getId));
    	Collections.reverse(patient);
        return patient; 
    }

    public Patient getPatientById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void deletePatient(Long id) {
        repo.deleteById(id);
    }

	public void updatePatient(Long id, Patient dto) {
		
		Patient pat = repo.findById(id).get();
        // update patient fields
      pat.setAge(dto.getAge());
      pat.setContact(dto.getContact());
      pat.setEmmergenceName(dto.getEmmergenceName());
      pat.setEmmergencyContact(dto.getEmmergencyContact());
      pat.setGender(dto.getGender());
      pat.setMaritalStatus(dto.getMaritalStatus());
      pat.setName(dto.getName());
      pat.setOccupation(dto.getOccupation());
      pat.setResidence(dto.getResidence());
      pat.addTracking("EDIT", "Updated  this patient");
		
      repo.save(pat);
		
	}

	public Patient findById(Long patientId) {
		// TODO Auto-generated method stub
		  return repo.findById(patientId).orElse(null);
	}

	public List<Patient> listPatients(String searchQuery) {
		if((searchQuery == null || searchQuery.isEmpty())) {
			List<Patient> patient =repo.findAll();
	    	patient.sort(Comparator.comparing(Patient::getId).reversed());
			return patient;
		} else {
			List<Patient> patient =repo.findByNameContainingIgnoreCase(searchQuery);
	    	patient.sort(Comparator.comparing(Patient::getId).reversed());
	    	
	    	return patient;
		}
		

	}
	
	public Long getTotalPatients() {
        return repo.getTotalPatients();
    }

    public Long getNewPatientsThisMonth() {
        return repo.getNewPatientsThisMonth();
    }
	public Patient findByName (String name) {
		return repo.findFirstByNameContainingIgnoreCase(name).get();
	}
}
