package com.ppp.billing.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppp.billing.model.EstimatePricing;
import com.ppp.billing.model.Invoice;
import com.ppp.billing.model.Job;
import com.ppp.billing.serviceImpl.EstimatePricingServiceImpl;
import com.ppp.billing.serviceImpl.InvoiceServiceImpl;

@Controller
@RequestMapping("invoice")
public class InvoiceController {
	
	@Autowired
	private InvoiceServiceImpl invoiceServiceImpl;
	
	@Autowired
	private EstimatePricingServiceImpl estimatePricingServiceImpl;
	
	@InitBinder
	public void customDateEditor(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		simpleDateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, false));
	}
	
	@GetMapping("/list")
	public String listinvoices(Model model) {
		try {
			List<Invoice> result = invoiceServiceImpl.listInvoice();
			double netPayable =0;
			for(Invoice invoice :result) {
				
				netPayable += invoice.getNetPayable();
			}
			model.addAttribute("netPayable", netPayable);
			model.addAttribute("results", result);

			return "billing/invoice/list-invoices";
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@GetMapping("/job-invoice/{id}")
	public String getInvoice(@PathVariable long id, Model model) {		
		try {
			Invoice invoicefinded = invoiceServiceImpl.findById(id);
			Job jobs = invoicefinded.getEstimatePricing().getJobEstimate().getJob();
			double discount = (invoicefinded.getDiscountPercentage()/100)*(invoicefinded.getEstimatePricing().getTotalPrice());
			model.addAttribute("discount", discount);
			model.addAttribute("job", jobs);
			model.addAttribute("invoices", invoicefinded);
			
			return "billing/estimate/invoice-view";
		} catch (Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/find-by/{startDate}/{endDate}")
	public String findByCreationDateBetwen(@PathVariable Date startDate, @PathVariable Date endDate, Model model) {
		try {
			List<Invoice> invoices = invoiceServiceImpl.findByCreationDateBetwen(startDate, endDate);
			double netPayable  = 0;
			
			for(Invoice invoice :invoices ) {
				netPayable+=invoice.getNetPayable();
			}
			model.addAttribute("invoices", invoices);
			model.addAttribute("netPayable", netPayable);

			return "tables/find-invoices";
		} catch (Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/job-invoice/from-pricing/{id}")
	public String getInvoiceFromPricing(@PathVariable long id, Model model) {	
		
		try {
			EstimatePricing estimatePricing = estimatePricingServiceImpl.findById(id).get();
			long index = estimatePricing.getInvoices().get(0).getId();
			Invoice invoicefinded = invoiceServiceImpl.findById(index);
			Job jobs = invoicefinded.getEstimatePricing().getJobEstimate().getJob();
			model.addAttribute("job", jobs);
			model.addAttribute("invoices", invoicefinded);
			
			return "billing/estimate/invoice-view";
		} catch (Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/invoice-taxs/{id}/{irTax}/{vatTax}")
	public String  setIrtaxAndVatTax(@PathVariable long id, @PathVariable double irTax,@PathVariable double vatTax, Model model) {
		try {
			Invoice invoice = invoiceServiceImpl.setIrtaxAndVatTax(id, irTax, vatTax);
			Job jobs = invoice.getEstimatePricing().getJobEstimate().getJob();
			model.addAttribute("job", jobs);
			model.addAttribute("invoices", invoice);
			return "billing/invoice/invoice-tva-result";
		} catch (Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/invoice-discount/{id}")
	public String displayDiscountapplicationForm(@PathVariable long id, Model model) {
		try {
			Invoice invoice = invoiceServiceImpl.findById(id);
			Job job = invoice.getEstimatePricing().getJobEstimate().getJob();
			double discount = (invoice.getDiscountPercentage()/100)*(invoice.getEstimatePricing().getTotalPrice());
			model.addAttribute("invoices", invoice);
			model.addAttribute("job", job);
			model.addAttribute("discount", discount);
			return "billing/invoice/apply-discount";
		} catch (Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/invoice-discount/{id}/{discount}")
	public String applyDiscount(@PathVariable long id,@PathVariable  double discount, Model model) {
		try {
			Invoice invoice = invoiceServiceImpl.applyDiscount(id, discount);
			double discountValue = (invoice.getDiscountPercentage()/100)*(invoice.getEstimatePricing().getTotalPrice());
			model.addAttribute("invoices", invoice);
			model.addAttribute("discount", discountValue);
			return "billing/invoice/apply-discount-result";
		} catch (Exception e) {
			throw e;
		
		}
	}

	
	@GetMapping("/job-tax-form/{id}")
	public String getTaxForm(@PathVariable long id, Model model) {		
		try {
			Invoice invoicefinded = invoiceServiceImpl.findById(id);
			Job jobs = invoicefinded.getEstimatePricing().getJobEstimate().getJob();
			model.addAttribute("job", jobs);
			model.addAttribute("invoices", invoicefinded);
			
			return "billing/invoice/invoice-irtax-tva";
		} catch (Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/display-taxs/{id}/{irTax}/{vatTax}")
	public String  displayIrtaxAndVatTax(@PathVariable long id, @PathVariable double irTax,@PathVariable double vatTax, Model model) {
		try {
			
			Invoice invoice = invoiceServiceImpl.displayIrtaxAndVatTax(id, irTax, vatTax);
			Job jobs = invoice.getEstimatePricing().getJobEstimate().getJob();
			model.addAttribute("job", jobs);
			model.addAttribute("invoices", invoice);
			return "billing/invoice/invoice-tva-result";
		} catch (Exception e) {
			throw e;
		}
	}
	
}
