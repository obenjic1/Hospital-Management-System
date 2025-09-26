package com.ppp.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppp.billing.model.ConsultationSubtype;
import com.ppp.billing.model.VisitService;
import com.ppp.billing.service.ConsultationSubtypeService;
import com.ppp.billing.service.ConsultationTypeService;
import com.ppp.billing.service.FactureService;
import com.ppp.billing.service.PatientService;
import com.ppp.billing.service.StaffService;

@Controller
@RequestMapping("/visit")
public class VisitController {
	
	

	 @Autowired
	    private ConsultationTypeService consultation;

	    @Autowired 
	    private ConsultationTypeService consultationTypeService;

//	    @Autowired
//	    private ConsultationSubtypeService subtypeService;
//	    
	    private final PatientService patientService;
	    private final StaffService staffs;

	    public VisitController(
	                           PatientService patientService,
	                          StaffService staffs) {
	        this.patientService = patientService;
	        this.staffs = staffs;
	    }

	    @GetMapping("/new")
	    public String showVisitForm(Model model) {
	        model.addAttribute("visitForm", new VisitForm());
	        model.addAttribute("patients", patientService.getAllPatients());
	        model.addAttribute("typeService", consultation.findAll());
	        model.addAttribute("doctor", staffs.getAllDoctors());
	        model.addAttribute("staffs", staffs.findAll());
	        model.addAttribute("consultations",consultationTypeService.findAll());

	        return "visit/visit";  
	    }
//
//	    // Handle form submission
//	    @PostMapping("/save")
//	    public String saveVisit(@ModelAttribute VisitForm visitForm) {
//	        Patient patient = patientService.getPatientById(visitForm.getPatientId());
//	        SubService subService = subServiceService.getSubServiceById(visitForm.getSubServiceId());
//
//	        // Create Visit
//	        Visit visit = new Visit();
//	        visit.setPatient(patient);
//	        visit.setNotes(visitForm.getNotes());
//	        visit.setVisitDate(new java.util.Date());
//	        visit.setServiceType(subService.getServiceType());
//
//	        Visit savedVisit = visitService.saveVisit(visit);
//
//	        // Auto-create Facture
//	        Facture facture = new Facture();
//	        facture.setVisit(savedVisit);
//	        facture.setAmount(subService.getPrice());
//	        facture.setStatus("UNPAID");
//
//	        factureService.saveFacture(facture);
//
//	        return "redirect:/factures/view/" + facture.getId();
//	    }

}
