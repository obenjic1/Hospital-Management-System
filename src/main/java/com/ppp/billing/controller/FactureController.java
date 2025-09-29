package com.ppp.billing.controller;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ppp.billing.model.Facture;
import com.ppp.billing.service.FactureService;

@Controller
@RequestMapping("/factures")
public class FactureController {

	@Autowired
	private FactureService factureService;
	
	
   @GetMapping
   public String list(@RequestParam(value = "name",   required = false) String patientName,
                      @RequestParam(value = "from",   required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                      @RequestParam(value = "to",     required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to,
                      @RequestParam(value = "status", required = false) String status,
                      Model model) {
	   
	  

       List<Facture> result;
      if (patientName != null && !patientName.trim().isEmpty()) {
           result = factureService.findByVisit_Patient_NameContainingIgnoreCaseOrderByIdDesc(patientName);
       } else if (from != null && to != null) {
           result = factureService.findByCreatedAtBetweenOrderByIdDesc(from, to);
       } else {
           result = factureService.findAllByOrderByIdDesc();   // or paginated
       }

       /* =====  NEW: quick status filter  ===== */
       if ("pending".equalsIgnoreCase(status)) {
    	    result = result.stream()
    	                   .filter(f -> !f.isFullyPaid())   // primitive boolean
    	                   .collect(Collectors.toList());   // or .collect(toList()) with static import
    	}

       model.addAttribute("factures", result);
       return "facture/list";
   }
   
   @GetMapping("/{id}/payments/new")
   public String paymentModal(@PathVariable Long id, Model model) {
       Facture f = factureService.getFactureById(id);
       model.addAttribute("facture", f);
       return "facture/payment-modal";   // the JSP fragment
   }
   
}
