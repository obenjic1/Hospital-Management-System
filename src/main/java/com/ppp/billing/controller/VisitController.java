package com.ppp.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppp.billing.Dto.VisitFormDTO;
import com.ppp.billing.service.ConsultationTypeService;
import com.ppp.billing.service.PatientService;
import com.ppp.billing.service.StaffService;
import com.ppp.billing.serviceImpl.VisitServiceImpl;

@Controller
@RequestMapping("/visit")
public class VisitController {
	
	

	 @Autowired
	    private ConsultationTypeService consultation;

	    @Autowired 
	    private ConsultationTypeService consultationTypeService;
	    @Autowired 
	    private VisitServiceImpl  visitService;;

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
	        model.addAttribute("doctors", staffs.getAllDoctors());
	        model.addAttribute("staffs", staffs.findAll());
	        model.addAttribute("consultations",consultationTypeService.findAll());

	        return "visit/visit";  
	    }
	    
	    @CrossOrigin
	    @PostMapping
	    public String saveVisit(@ModelAttribute VisitFormDTO dto) {
	    	System.out.println(dto);
	    	visitService.saveVisit(dto);
	        return "ok"; }

}
