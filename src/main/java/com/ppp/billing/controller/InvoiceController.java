package com.ppp.billing.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppp.billing.model.Invoice;
import com.ppp.billing.model.JobEstimate;
import com.ppp.billing.serviceImpl.InvoiceServiceImpl;

@Controller
@RequestMapping("invoice")
public class InvoiceController {
	
	@Value("${folder.invoice}")
	private String invoiceDir;
	
	
	@Autowired
	private InvoiceServiceImpl invoiceServiceImpl;
	
	
	
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
			Collections.reverse(result);
			double netPayable =0;
			for(Invoice invoice :result) {
				
				netPayable += invoice.getNetPayable();
			}
			
			int totalElement = result.size();
			model.addAttribute("netPayable", netPayable);
			model.addAttribute("totalElement", totalElement);
			model.addAttribute("results", result);

			return "billing/invoice/list-invoices";
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	/*
	 * Invoices Methodes
	 */
	@GetMapping("/generate/invoice/{id}")
	@ResponseBody
	public String generateInvoice(@PathVariable long id, Model model) {
		invoiceServiceImpl.saveInvoice(id);
		//EstimatePricing estimate = estimatePricingServiceImpl.findById(id).get();
		//Job job = estimate.getJobEstimate().getJob();
		return "OK";
	}
	
	/*
	 * Invoicing with discount  Methode
	 */
	@GetMapping("/generateDiscount/invoice/{id}/{qty}")
	@ResponseBody
	public String generateDiscountInvoice(@PathVariable long id, @PathVariable int qty, Model model) {
		invoiceServiceImpl.saveInvoiceWithDiscount(id,qty);
		return "OK";
	}
	
	
	
	@GetMapping("/commission-result/{id}/{qty}")
	public String getInvoice(@PathVariable long id,@PathVariable long qty, Model model) {		
			try {
				//long lastInvoice = correspondingestimatePricing.getInvoices().getS;
				Invoice invoice = new Invoice();
				
			
			
			model.addAttribute("invoices", invoice);
			
			return "billing/estimate/invoice-view";
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	
	@GetMapping("/job-invoice/{id}")
	public String getInvoice(@PathVariable long id, Model model) {		
		try {
			
			
			
			return "billing/estimate/invoice-view";
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	
	
	
	@GetMapping("/commission-invoice/{id}/{qty}")
	public String getCommissionInvoice(@PathVariable long id,@PathVariable int qty, Model model) {		
		try {
			
			//long lastInvoice = correspondingestimatePricing.getInvoices().getS;
			
			
			return "billing/estimate/commission-invoice-view";
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

			return "billing/estimate/invoice-view";
		} catch (Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/discount/from-pricing/{id}/{qty}")
	public String getInvoiceFromPricing(@PathVariable long id, @PathVariable long qty, Model model) {	
		try {
		
			return "billing/estimate/invoice-view";
		} catch (Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/invoice-taxs/{id}/{irTax}/{vatTax}")
	public String  setIrtaxAndVatTax(@PathVariable long id, @PathVariable double irTax,@PathVariable double vatTax, Model model) {
		try {
		
			model.addAttribute("isApplyTax", 1);
			return "billing/invoice/invoice-tva-result";
		} catch (Exception e) {
			throw e;
		}
	}
	
	//@PreAuthorize("hasAuthority('ROLE_APPLY_DISCOUNT')")
	@GetMapping("/invoice-discount/{id}")
	public String displayDiscountapplicationForm(@PathVariable long id, Model model) {
		try {
			Invoice invoice = invoiceServiceImpl.findById(id);
			
			model.addAttribute("invoices", invoice);
		
			return "billing/invoice/apply-discount";
		} catch (Exception e) {
			throw e;
		}
	}
	
	// -------------- get discount by percentage-----------------
	@GetMapping("/invoice-discount/{id}/{discount}")
	public String applyDiscount(@PathVariable long id,@PathVariable  double discount, Model model) {
		try {
			
		
			return "billing/invoice/apply-discount-result";
		} catch (Exception e) {
			throw e;
		
		}
	}
	// -------------- get discount by Amount-----------------
		@GetMapping("/invoice-discount-amount/{id}/{discount}")
		public String applyDiscountAmount(@PathVariable long id,@PathVariable  double discount, Model model) {
			try {
				Invoice invoice = invoiceServiceImpl.applyDiscountAmount(id, discount);
				
				model.addAttribute("invoices", invoice);
			
				return "billing/invoice/apply-discount-result";
			} catch (Exception e) {
				throw e;
			
			}
		}

	
	@GetMapping("/job-tax-form/{id}")
	public String getTaxForm(@PathVariable long id, Model model) {		
		try {
			Invoice invoice = invoiceServiceImpl.findById(id);
			
			model.addAttribute("invoices", invoice);
			
			
			return "billing/invoice/invoice-irtax-tva";
		} catch (Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/display-taxs/{id}/{irTax}/{vatTax}")
	public String  displayIrtaxAndVatTax(@PathVariable long id, @PathVariable double irTax,@PathVariable double vatTax, Model model) {
		try {
			
			Invoice invoice = invoiceServiceImpl.displayIrtaxAndVatTax(id, irTax, vatTax);
		
			model.addAttribute("invoices", invoice);
			model.addAttribute("isApplyTax", 0);
			return "billing/invoice/invoice-tva-result";
		} catch (Exception e) {
			throw e;
		}
	}
	/*
	 * creating and printing invoice pdf
	 */
	
	@PreAuthorize("hasAuthority('ROLE_APPLY_DISCOUNT')")
	@GetMapping("/invoice-pdf/{reference}")
	@ResponseBody
	public String generateInvoicePdf(@PathVariable String reference) throws IOException {
	    return invoiceServiceImpl.createInvoiceDataPdf(reference, false, true);
	}
	
	@PreAuthorize("hasAuthority('ROLE_APPLY_DISCOUNT')")
	@GetMapping("/invoice-pdf-commission/{reference}")
	@ResponseBody
	public String generateInvoicePdfCommission(@PathVariable String reference) throws IOException {
		return invoiceServiceImpl.createInvoiceDataPdf(reference, true, true);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_APPLY_DISCOUNT')")
	@GetMapping("/invoice-pdf-display/{reference}")
	@ResponseBody
	public String generateInvoicePdfDisplay(@PathVariable String reference) throws IOException {
	    return invoiceServiceImpl.createInvoiceDataPdf(reference, false, false);
	}

	@PreAuthorize("hasAuthority('ROLE_APPLY_DISCOUNT')")
	@GetMapping("/invoice-pdf-apply/{reference}")
	@ResponseBody
	public String generateInvoicePdfApply(@PathVariable String reference) throws IOException {
	    return invoiceServiceImpl.createInvoiceDataPdf(reference, false, true);
	}
	
	
	// Confirm a Job 
	//@PreAuthorize("hasAuthority('ROLE_VALIDATE_INVOICE')")
	@PostMapping("/confirm-invoice/{referenceNumber}")
	public ResponseEntity<String> confirmJob (@PathVariable String referenceNumber) {
		try {
			 
			    	invoiceServiceImpl.confirmInvoice(referenceNumber);
			    	return new ResponseEntity<String>("OK", HttpStatus.OK);
			    
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  new ResponseEntity<String>("KO", HttpStatus.BAD_REQUEST);
	}
	
	
	}

	