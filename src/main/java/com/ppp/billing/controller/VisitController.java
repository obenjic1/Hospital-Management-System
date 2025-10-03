package com.ppp.billing.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ppp.billing.Dto.DoctorConsultationStatsDTO;
import com.ppp.billing.Dto.SubtypeStatsDTO;
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
	    
	    
	    
	    @PostMapping("/save")
	    public ResponseEntity<String> saveVisit(@ModelAttribute VisitFormDTO dto) {
	    	  try {
	    		  visitService.saveVisit(dto);
	           	return new ResponseEntity<>(HttpStatus.CREATED);
			} catch (Exception e) {
				
				 return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);	}
	    }
	    
	    @GetMapping("/subtype")
	    public String subtypeStats(@RequestParam(defaultValue="daily") String period, Model model) {
	        List<SubtypeStatsDTO> stats;
	        LocalDate today = LocalDate.now();
	        switch(period) {
	            case "weekly":
	                stats = visitService.getWeeklySubtypeStats(today);
	                break;
	            case "monthly":
	                stats = visitService.getDailySubtypeStats(today);
	                break;
	            default:
	            	  stats = visitService.getMonthlySubtypeStats(today);
	        }

	        model.addAttribute("stats", stats);
	        model.addAttribute("period", period);
	        return "stats/rep";  // JSP page
	    }
	    
	    @GetMapping("/statistics/doctor-consultations")
	    public String doctorConsultationStats(
	            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
	            Model model) {
	    	

	        if (startDate == null) startDate = LocalDate.now().withDayOfMonth(1);
	        if (endDate == null) endDate = LocalDate.now();

	        List<DoctorConsultationStatsDTO> stats = visitService.getDoctorConsultationStats(startDate, endDate);

	        // Compute totals
	        long totalConsultations = stats.stream().mapToLong(DoctorConsultationStatsDTO::getConsultationCount).sum();
	        BigDecimal totalRevenue = stats.stream().map(DoctorConsultationStatsDTO::getTotalAmount)
	                                       .reduce(BigDecimal.ZERO, BigDecimal::add);
	        BigDecimal totalDoctorPay = stats.stream().map(DoctorConsultationStatsDTO::getDoctorPay)
	                                         .reduce(BigDecimal.ZERO, BigDecimal::add);
	        BigDecimal totalHospitalProfit = stats.stream().map(DoctorConsultationStatsDTO::getHospitalProfit)
	                                              .reduce(BigDecimal.ZERO, BigDecimal::add);

	        model.addAttribute("stats", stats);
	        model.addAttribute("totalConsultations", totalConsultations);
	        model.addAttribute("totalRevenue", totalRevenue);
	        model.addAttribute("totalDoctorPay", totalDoctorPay);
	        model.addAttribute("totalHospitalProfit", totalHospitalProfit);
	        model.addAttribute("startDate", startDate);
	        model.addAttribute("endDate", endDate);

	        return "reports/dashboard";
	    }

}
