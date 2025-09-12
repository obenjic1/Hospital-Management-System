package com.ppp.billing.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.Medicine;
import com.ppp.billing.model.Patient;
import com.ppp.billing.model.Medicine.Location;
import com.ppp.billing.repository.PatientRepository;

@Service
public class PatientService {

	
	@Autowired
    private PatientRepository repo;

    public Patient createPatient(Patient newPatient) {
    	Patient patient = new Patient();
    	patient.setAge(newPatient.getAge());
    	patient.setContact(newPatient.getContact());
    	patient.setGender(newPatient.getGender());
    	patient.setName(newPatient.getName());
    	patient.setEmmergenceName(newPatient.getEmmergenceName());
    	patient.setEmmergencyContact(newPatient.getEmmergencyContact());
    	patient.addTracking("CREATE", "Added this patient");
        return repo.save(patient);
    }

    public List<Patient> getAllPatients() {
    	List<Patient> patient =repo.findAll();
    	patient.sort(Comparator.comparing(Patient::getName));
    //	Collections.reverse(patient);
        return patient; 
    }

    public Patient getPatientById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void deletePatient(Long id) {
        repo.deleteById(id);
    }

	public void updatePatient(Long id, Patient updatedPatient) {
		Patient patient = repo.findById(id).get();
    	patient.addTracking("EDIT", "Updated  this patient");

		
		// TODO Auto-generated method stub
		
	}

	public Patient findById(Long patientId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Patient> listPatients(String searchQuery) {
		if((searchQuery == null || searchQuery.isEmpty())) {
			List<Patient> patient =repo.findAll();
	    	patient.sort(Comparator.comparing(Patient::getId));
			return patient;
		} else {
			List<Patient> patient =repo.findByNameContainingIgnoreCase(searchQuery);
	    	patient.sort(Comparator.comparing(Patient::getId));
	    	return patient;
		}
		

	}
}
