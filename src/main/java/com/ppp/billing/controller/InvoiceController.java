package com.ppp.billing.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppp.billing.model.Invoice;
import com.ppp.billing.model.Job;
import com.ppp.billing.serviceImpl.InvoiceServiceImpl;

@Controller
@RequestMapping("invoice")
public class InvoiceController {
	
	@Autowired
	private InvoiceServiceImpl invoiceServiceImpl;
	
	@GetMapping("/list")
	public String listinvoices(Model model) {
		try {
			List<Invoice> result = invoiceServiceImpl.listInvoice();
			model.addAttribute("results", result);
			return "billing/invoice/list-invoices";
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@GetMapping("/job-invoice/{id}")
	public String getInvoice(@PathVariable long id, Model model) throws Exception {		
		try {
			Invoice invoicefinded = invoiceServiceImpl.findById(id);
			Job jobs = invoicefinded.getEstimatePricingid().getJobEstimate().getJob();
			model.addAttribute("job", jobs);
			model.addAttribute("invoices", invoicefinded);
			
			return "billing/estimate/invoice-view";
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	@GetMapping("/find-by/{startDate}/{endDate}")
	public String findByCreationDateBetwen(Date startDate, Date endDate, Model model) {
		try {
			List<Invoice> invoices = invoiceServiceImpl.findByCreationDateBetwen(startDate, endDate);
			model.addAttribute("invoices", invoices);
			return "ok";
		} catch (Exception e) {
			throw e;
		}
	}

}
