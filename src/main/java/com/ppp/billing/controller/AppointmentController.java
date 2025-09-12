package com.ppp.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ppp.billing.Dto.AppointmentDto;
import com.ppp.billing.service.AppoitmentService;
import com.ppp.billing.service.PatientService;
import com.ppp.billing.service.StaffService;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppoitmentService appointmentService;
	 @Autowired
	    private PatientService patientService;
	 
	 @Autowired
	    private StaffService staffService;

    @GetMapping
    public String listAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "appointment/appointments"; 
    }

    @PostMapping
    public String createAppointment(@ModelAttribute AppointmentDto appointment, RedirectAttributes redirectAttributes) {
       appointmentService.createAppointment(appointment);
        redirectAttributes.addFlashAttribute("success", "Appointment created successfully!");
        return "appointment/appointments";
    }

    @GetMapping("/cancel/{id}")
    public String cancelAppointment(@PathVariable Long id) {
        appointmentService.cancelAppointment(id);
        return "redirect:/appointments";
    }
    
    @GetMapping("/new")
    public String appointmentForm(@RequestParam Long patientId ,  Model model) {
        model.addAttribute("patient", patientService.getPatientById(patientId));
        model.addAttribute("doctors",staffService.getAllDoctors());

        return "appointment/create-appointment";
    }
}