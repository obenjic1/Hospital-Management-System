package com.ppp.billing.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ppp.billing.Dto.PayrollDto;
import com.ppp.billing.Dto.PayrollSummaryDto;
import com.ppp.billing.model.Payroll;
import com.ppp.billing.model.Staff;
import com.ppp.billing.service.PayrollService;
import com.ppp.billing.service.StaffService;

@Controller
@RequestMapping("/payroll")
public class PayrollController {
	
	
	 @Autowired
	    private PayrollService payrollService;
	 @Autowired 
	 StaffService staffService;

	    @GetMapping
	    public String listPayrolls(Model model) {
	        model.addAttribute("payrolls", payrollService.getAllPayrolls());
	        return "payroll/list";  // JSP page
	    }

	    @GetMapping("/create")
	    public String createPayrollForm(Model model) {
	        PayrollDto payroll = new PayrollDto();
	       // Staff staff = new Staff();
	        List<Staff> staffs = staffService.findAll();
	        model.addAttribute("payroll", payroll);
	        model.addAttribute("staffs", staffs);
	        return "payroll/create";  // JSP page
	    }

	    @PostMapping("/save")
	    public ResponseEntity<String> savePayroll(@ModelAttribute PayrollDto payroll) {
	        try {
	             payrollService.createPayroll(payroll);
	            return new ResponseEntity<>("Success", HttpStatus.OK);
	        } catch (Exception e) {
	            System.err.println("Error saving payroll: " + e.getMessage());
	            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
	        }
	    }
	    
	    @GetMapping("/payroll-summary")
	    public String payrollSummary(Model model) {
	        List<PayrollSummaryDto> summaries = payrollService.getPayrollSummary();
	        model.addAttribute("summaries", summaries);
	        return "reports/payroll-summary";
	    }
	    
	    @GetMapping("/payslip/{id}")
	    public ResponseEntity<ByteArrayInputStream> downloadPayslip(@PathVariable Long id) {
	        Payroll payroll = payrollService.getPayrollById(id);

	        ByteArrayInputStream bis = payrollService.generatePayslip(payroll);

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "inline; filename=payslip_" + id + ".pdf");

	        return ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(bis);
	    }

}
