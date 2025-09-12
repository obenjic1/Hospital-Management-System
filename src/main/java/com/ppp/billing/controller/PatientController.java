package com.ppp.billing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ppp.billing.model.Appointment;
import com.ppp.billing.model.Consultation;
import com.ppp.billing.model.Medicine;
import com.ppp.billing.model.Patient;
import com.ppp.billing.service.AppoitmentService;
import com.ppp.billing.service.ConsultationService;
import com.ppp.billing.service.PatientService;

@Controller
@RequestMapping("/patients")
public class PatientController {
	
	 @Autowired
	    private PatientService patientService;
	 @Autowired
	    private AppoitmentService appointmentService;
	 
	 @Autowired
	    private ConsultationService consultationService;

	    @GetMapping
	    public String listPatients( @RequestParam(value = "name", required = false) String name,Model model) {
	    	  List<Patient> patients = patientService.listPatients( name);    
	           model.addAttribute("patients",patients);
	        return "patient/list-patient";
	    }

	    @GetMapping("/new")
	    public String showCreateForm(Model model) {
	        model.addAttribute("patient", new Patient());
	        return "patient/create-form";
	    }

	    
	    @PostMapping
	    public ResponseEntity<String> createPatient(@ModelAttribute Patient patient) {
	        
	        try {
	        	patientService.createPatient(patient);
	            return new ResponseEntity<>("Success", HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
	        }
	    }

	    @GetMapping("/edit/{id}")
	    public String showEditForm(@PathVariable Long id, Model model) {
	        model.addAttribute("patient", patientService.getPatientById(id));
	        return "patients/form";
	    }

	    @PostMapping("/update/{id}")
	    public String updatePatient(@PathVariable Long id, @ModelAttribute Patient patient) {
	        patientService.updatePatient(id, patient);
	        return "redirect:/patients";
	    }

	    @GetMapping("/delete/{id}")
	    public String deletePatient(@PathVariable Long id) {
	        patientService.deletePatient(id);
	        return "redirect:/patients";
	    }
	    
	    @GetMapping("/history/{id}")
	    public String getHitory(@PathVariable Long id,Model model) {
	        patientService.getPatientById(id);
	        model.addAttribute("patients",  patientService.getPatientById(id));
	        return "patient/history";
	    }
	    @GetMapping("/view/{id}")
	    public String viewPatient(@PathVariable Long id,Model model) {
	        patientService.getPatientById(id);	      
	        List<Appointment> appointments = appointmentService.findByPatient( patientService.getPatientById(id));

	        model.addAttribute("appointments",  appointments);

	        model.addAttribute("patient",  patientService.getPatientById(id));
	        return "patient/detail";
	    }
	    
	    @GetMapping("/patients/{patientId}/history")
	    public String viewPatientHistory(@PathVariable Long patientId, Model model) {
	        Patient patient = patientService.getPatientById(patientId);
	        List<Appointment> appointments = appointmentService.findByPatient(patient);
	        
	  //      List<Consultation> consultations = consultationService.consultationService(appointments);

	        model.addAttribute("patient", patient);
	        model.addAttribute("appointments", appointments);
	    //    model.addAttribute("consultations", consultations);

	        return "patients/history";
	    }

	  
}
