package com.ppp.billing.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.ppp.billing.Dto.ConsultationDto;
import com.ppp.billing.Dto.DoctorRevenueSummary;
import com.ppp.billing.model.Appointment;
import com.ppp.billing.model.AppointmentStatus;
import com.ppp.billing.model.Consultation;
import com.ppp.billing.model.ConsultationSubtype;
import com.ppp.billing.model.Patient;
import com.ppp.billing.repository.AppointmentRepository;
import com.ppp.billing.service.ConsultationService;
import com.ppp.billing.service.ConsultationSubtypeService;
import com.ppp.billing.service.ConsultationTypeService;
import com.ppp.billing.service.PatientService;
import com.ppp.billing.service.PdfService;
import com.ppp.billing.service.StaffService;


@Controller
@RequestMapping("/consultations")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private AppointmentRepository appointmentRepository;
    
  
    
    @Autowired
    private StaffService staffService;

    @Autowired
    private PatientService patientService;
    
    @Autowired
    private PdfService pdfService;
    
    @Autowired
    private ConsultationSubtypeService consultationSubtypeService;
    
    @Autowired ConsultationTypeService consultationTypeService;
    
    @GetMapping
    public String listConsultations(Model model) {
        model.addAttribute("consultations", consultationService.getAllConsultations());
        return "Consultation/list-consultation"; // JSP page
    }

    @GetMapping("/new/{id}")
    public String newConsultation(@PathVariable Long id, Model model) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Consultation consultation = new Consultation();
        consultation.setAppointment(appointment);
        model.addAttribute("consultation", consultation);
        return "Consultation/create-consultation"; // JSP form page
    }
    
    @GetMapping("/new/consultation")
    public String Consultation( Model model) {
        Consultation consultation = new Consultation();
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors",staffService.getAllDoctors());
        model.addAttribute("consultationTypes",consultationTypeService.findAll());


        model.addAttribute("consultation", consultation);
        return "Consultation/create-consultation"; // JSP form page
    }
    
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> saveConsultation(@ModelAttribute ConsultationDto consultationDto) {
        Consultation savedConsultation = consultationService.createConsultation(consultationDto);

        Map<String, Object> response = new HashMap<>();
        response.put("id", savedConsultation.getId());
        response.put("referenceNumber", savedConsultation.getReferenceNumber());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public String viewConsultation(@PathVariable Long id, Model model) {
       Optional<Consultation> consultation = consultationService.getConsultationByAppointment(id);
        model.addAttribute("consultation", consultation);
        return "consultation-details"; // JSP page to display consultation
    }
    
    @GetMapping("/consultations/walkin")
    public String newWalkInConsultation(Model model) {
        Consultation consultation = new Consultation();

        // Create a temporary "anonymous" patient record
        Patient walkIn = new Patient();
        walkIn.setName("Walk-in Patient");
        walkIn.setGender("Unknown");
        walkIn.setContact("N/A");

        Appointment appointment = new Appointment();
        appointment.setPatient(walkIn);
      //  appointment.setAppointmentDate(LocalDateTime.now());
        appointment.setStatus(AppointmentStatus.COMPLETED);

        consultation.setAppointment(appointment);

        model.addAttribute("consultation", consultation);
        model.addAttribute("isWalkIn", true); // so the JSP can behave differently
        return "consultation-form";
    }
    
    @GetMapping ("/consultation")
    public String listConsultation(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model) {

        List<Consultation> consultations;

        if (startDate != null && endDate != null) {
            consultations = consultationService.findByDateRange(startDate.atStartOfDay(), endDate.plusDays(1).atStartOfDay());
        } else {
            consultations = consultationService.getAllConsultations();
        }

        model.addAttribute("consultations", consultations);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "consultations/list";
    }
    
    @GetMapping("/monthly")
    public String viewMonthlyConsultations(@RequestParam(required = false) Integer month,
                                           @RequestParam(required = false) Integer year,
                                           Model model) {
        LocalDate now = LocalDate.now();
        int currentMonth = (month != null) ? month : now.getMonthValue();
        int currentYear = (year != null) ? year : now.getYear();

        List<Consultation> consultations = consultationService.getConsultationsByMonth(currentMonth, currentYear);
        Map<String, Long> doctorCounts = consultationService.getConsultationCountByDoctor(currentMonth, currentYear);

        model.addAttribute("consultations", consultations);
        model.addAttribute("doctorCounts", doctorCounts);
        model.addAttribute("month", currentMonth);
        model.addAttribute("year", currentYear);

        return "Consultation/consultation-report"; // JSP page
    }
    
		
		@GetMapping("/doctor-revenue")
		public String getDoctorRevenue(
		        @RequestParam(required = false) Integer month,
		        @RequestParam(required = false) Integer year,
		        Model model) {
		
		    if (month == null || year == null) {
		        LocalDate now = LocalDate.now();
		        month = now.getMonthValue();
		        year = now.getYear();
		    }
		
		    DoctorRevenueSummary summary = consultationService.getMonthlyDoctorRevenueWithProfit(month, year);
		
		    model.addAttribute("defaultMonth", month);
		    model.addAttribute("defaultYear", year);
		    model.addAttribute("summary", summary);
		
		    return "Consultation/doctor-revenue";
		    }
		
	    @GetMapping("/{id}/receipt")
	    public ResponseEntity<Resource> getConsultationReceipt(@PathVariable Long id) {
	        try {
	            Consultation consultation = consultationService.findById(id)
	                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consultation not found"));

	            File pdfFile = pdfService.printConsultationReceipt(consultation);

	            InputStreamResource resource = new InputStreamResource(new FileInputStream(pdfFile));

	            HttpHeaders headers = new HttpHeaders();
	            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + pdfFile.getName());
	            headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

	            return ResponseEntity.ok()
	                .headers(headers)
	                .contentLength(pdfFile.length())
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(resource);

	        } catch (IOException e) {
	            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating PDF", e);
	        }
	    }

	    @GetMapping("/consultations/subtypes")
	    @ResponseBody
	    public List<ConsultationSubtype> getSubtypesByType(@RequestParam Long typeId) {
	        return consultationSubtypeService.findByConsultationTypeId(typeId);
	    }
}
