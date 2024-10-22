package com.ppp.billing.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.ppp.billing.model.EstimatePricing;
import com.ppp.billing.model.Invoice;
import com.ppp.billing.model.Job;
import com.ppp.billing.model.JobActivity;
import com.ppp.billing.model.JobEstimate;
import com.ppp.billing.model.JobPaper;
import com.ppp.billing.serviceImpl.EstimatePricingServiceImpl;
import com.ppp.billing.serviceImpl.InvoiceServiceImpl;
import com.ppp.billing.serviceImpl.JobEstimateServiceImpl;
import com.ppp.billing.serviceImpl.JobServiceImpl;
import com.ppp.printable.PrintableElement;

@Controller
@RequestMapping("invoice")
public class InvoiceController {
	
	@Value("${folder.invoice}")
	private String invoiceDir;
	
	
	@Autowired
	private InvoiceServiceImpl invoiceServiceImpl;
	
	@Autowired
	private EstimatePricingServiceImpl estimatePricingServiceImpl;
	
	@Autowired
	private JobEstimateServiceImpl estimateServiceImpl;
	
	@Autowired JobServiceImpl jobServiceImpl;
	
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
	

	
	@GetMapping("/job-invoice/{id}")
	public String getInvoice(@PathVariable long id, Model model) {		
		try {
			Invoice invoice = invoiceServiceImpl.findById(id);
			Job jobs = invoice.getEstimatePricing().getJobEstimate().getJob();
			double discount = (invoice.getDiscountPercentage()/100)*(invoice.getEstimatePricing().getTotalPrice());
			double irTaxValue= (invoice.getIrTaxPercentage()/100)*invoice.getEstimatePricing().getTotalPrice();
			double vatValue= (invoice.getVatPercentage()/100)*invoice.getEstimatePricing().getTotalPrice();
			model.addAttribute("irTaxValue", irTaxValue);
			model.addAttribute("vatValue", vatValue);
			model.addAttribute("discount", discount);
			model.addAttribute("job", jobs);
			model.addAttribute("invoices", invoice);
			
			return "billing/estimate/invoice-view";
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	
	
	
	@GetMapping("/commission-invoice/{id}/{qty}")
	public String getCommissionInvoice(@PathVariable long id,@PathVariable int qty, Model model) {		
		try {
			JobEstimate estimate = estimateServiceImpl.findById(id);
			List<EstimatePricing> estimatePricing = estimate.getEstimatePricings();
			EstimatePricing estimatePricingWithCommission = new EstimatePricing();
			//long lastInvoice = correspondingestimatePricing.getInvoices().getS;
			Invoice invoice = new Invoice();
			for(EstimatePricing correspondingestimatePricing :estimatePricing ) {
				
				if(correspondingestimatePricing.getQuantity() == qty) {
					
					estimatePricingWithCommission.setQuantity(correspondingestimatePricing.getQuantity());
					estimatePricingWithCommission.setTotalPrice(correspondingestimatePricing.getTotalPrice() +	estimate.getCommission() +  estimate.getDiscountValue());
					estimatePricingWithCommission.setUnitPrice(estimatePricingWithCommission.getTotalPrice()/correspondingestimatePricing.getQuantity());
					 invoice = correspondingestimatePricing.getInvoices().get(0);
				}
			}

			Job jobs = estimate.getJob();
		
//			Job jobs = invoice.getEstimatePricing().getJobEstimate().getJob();
			double discount = (invoice.getDiscountPercentage()/100)*(estimatePricingWithCommission.getTotalPrice());
			double irTaxValue= (invoice.getIrTaxPercentage()/100)*estimatePricingWithCommission.getTotalPrice();
			double vatValue= (invoice.getVatPercentage()/100)*estimatePricingWithCommission.getTotalPrice();
			double netPayable = estimatePricingWithCommission.getTotalPrice()+ irTaxValue + vatValue -discount;
			model.addAttribute("irTaxValue", irTaxValue);
			model.addAttribute("netPayable", netPayable);
			model.addAttribute("vatValue", vatValue);
			model.addAttribute("discount", discount);
			model.addAttribute("job", jobs);
			model.addAttribute("invoices", invoice);
			model.addAttribute("estimatePricingWithCommission", estimatePricingWithCommission);
			
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
	
	@GetMapping("/discount/from-pricing/{id}/{qty}")
	public String getInvoiceFromPricing(@PathVariable long id, @PathVariable long qty, Model model) {	
		try {
			JobEstimate jobEstimate = estimateServiceImpl.findById(id);
			List<EstimatePricing> estimatePricing = jobEstimate.getEstimatePricings();
			EstimatePricing discountestimatePricing = new EstimatePricing();
			for (EstimatePricing invoicedEstimatePricing : estimatePricing) {
				if(invoicedEstimatePricing.getQuantity() == qty) {
					discountestimatePricing = invoicedEstimatePricing;
				}
			}

			long index = discountestimatePricing.getInvoices().get(0).getId();
			Invoice invoice = invoiceServiceImpl.findById(index);
			Job job = invoice.getEstimatePricing().getJobEstimate().getJob();
			
			double discount = (invoice.getDiscountPercentage()/100)*(invoice.getEstimatePricing().getTotalPrice());
			double irTaxValue= (invoice.getIrTaxPercentage()/100)*invoice.getEstimatePricing().getTotalPrice();
			double vatValue= (invoice.getVatPercentage()/100)*invoice.getEstimatePricing().getTotalPrice();
			model.addAttribute("job", job);
			model.addAttribute("invoices", invoice);
			model.addAttribute("irTaxValue", irTaxValue);
			model.addAttribute("vatValue", vatValue);
			model.addAttribute("discount", discount);
			
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
			double irTaxValue= (invoice.getIrTaxPercentage()/100)*invoice.getEstimatePricing().getTotalPrice();
			double vatValue= (invoice.getVatPercentage()/100)*invoice.getEstimatePricing().getTotalPrice();
			double discount = (invoice.getDiscountPercentage()/100)*(invoice.getEstimatePricing().getTotalPrice());
			model.addAttribute("irTaxValue", irTaxValue);
			model.addAttribute("vatValue", vatValue);
			model.addAttribute("job", jobs);
			model.addAttribute("invoices", invoice);
			model.addAttribute("discountValue", discount);

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
			Job job = invoice.getEstimatePricing().getJobEstimate().getJob();
			double discount = (invoice.getDiscountPercentage()/100)*(invoice.getEstimatePricing().getTotalPrice());
			double irTaxValue= (invoice.getIrTaxPercentage()/100)*invoice.getEstimatePricing().getTotalPrice();
			double vatValue= (invoice.getVatPercentage()/100)*invoice.getEstimatePricing().getTotalPrice();
			model.addAttribute("job", job);
			model.addAttribute("invoices", invoice);
			model.addAttribute("irTaxValue", irTaxValue);
			model.addAttribute("vatValue", vatValue);
			model.addAttribute("discount", discount);
			return "billing/invoice/apply-discount";
		} catch (Exception e) {
			throw e;
		}
	}
	
	// -------------- get discount by percentage-----------------
	@GetMapping("/invoice-discount/{id}/{discount}")
	public String applyDiscount(@PathVariable long id,@PathVariable  double discount, Model model) {
		try {
			Invoice invoice = invoiceServiceImpl.applyDiscount(id, discount);
			double discountValue = (invoice.getDiscountPercentage()/100)*(invoice.getEstimatePricing().getTotalPrice());
			double irTaxValue= (invoice.getIrTaxPercentage()/100)*invoice.getEstimatePricing().getTotalPrice();
			double vatValue= (invoice.getVatPercentage()/100)*invoice.getEstimatePricing().getTotalPrice();
			model.addAttribute("invoices", invoice);
			model.addAttribute("discount", discountValue);
			model.addAttribute("irTaxValue", irTaxValue);
			model.addAttribute("vatValue", vatValue);
			return "billing/invoice/apply-discount-result";
		} catch (Exception e) {
			throw e;
		
		}
	}
	// -------------- get discount by Amount-----------------
		@GetMapping("/invoice-discount-amount/{id}/{discount}")
		public String applyDiscountAmount(@PathVariable long id,@PathVariable  double discount, Model model) {
			try {
				Invoice invoice = invoiceServiceImpl.applyDiscount(id, discount);
				double discountValue = (invoice.getDiscountPercentage()/100)*(invoice.getEstimatePricing().getTotalPrice());
				double irTaxValue= (invoice.getIrTaxPercentage()/100)*invoice.getEstimatePricing().getTotalPrice();
				double vatValue= (invoice.getVatPercentage()/100)*invoice.getEstimatePricing().getTotalPrice();
				model.addAttribute("invoices", invoice);
				model.addAttribute("discount", discountValue);
				model.addAttribute("irTaxValue", irTaxValue);
				model.addAttribute("vatValue", vatValue);
				return "billing/invoice/apply-discount-result";
			} catch (Exception e) {
				throw e;
			
			}
		}

	
	@GetMapping("/job-tax-form/{id}")
	public String getTaxForm(@PathVariable long id, Model model) {		
		try {
			Invoice invoice = invoiceServiceImpl.findById(id);
			Job jobs = invoice.getEstimatePricing().getJobEstimate().getJob();
			double irTaxValue= (invoice.getIrTaxPercentage()/100)*invoice.getEstimatePricing().getTotalPrice();
			double vatValue= (invoice.getVatPercentage()/100)*invoice.getEstimatePricing().getTotalPrice();
			double discount = (invoice.getDiscountPercentage()/100)*(invoice.getEstimatePricing().getTotalPrice());
			model.addAttribute("irTaxValue", irTaxValue);
			model.addAttribute("vatValue", vatValue);
			model.addAttribute("job", jobs);
			model.addAttribute("invoices", invoice);
			model.addAttribute("discount", discount);
			
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
			double irTaxValue= (invoice.getIrTaxPercentage()/100)*invoice.getEstimatePricing().getTotalPrice();
			double vatValue= (invoice.getVatPercentage()/100)*invoice.getEstimatePricing().getTotalPrice();
			double discount = (invoice.getDiscountPercentage()/100)*(invoice.getEstimatePricing().getTotalPrice());
			model.addAttribute("irTaxValue", irTaxValue);
			model.addAttribute("vatValue", vatValue);
			model.addAttribute("job", jobs);
			model.addAttribute("invoices", invoice);
			model.addAttribute("discount", discount);
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
	    return invoiceServiceImpl.createInvoiceDataPdf(reference);
	}
	}

	