package com.ppp.billing.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppp.billing.serviceImpl.InvoiceServiceImpl;

@Controller
@RequestMapping("invoice")
public class InvoiceController {
	
	@Autowired
	private InvoiceServiceImpl invoiceServiceImpl;
	
	@GetMapping("/list")
	public String listinvoices(Model model) {
		model.addAttribute("results", invoiceServiceImpl.listInvoice());
		return "billing/invoice/list-invoices";
	}

}
