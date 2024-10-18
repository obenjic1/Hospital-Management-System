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
					estimatePricingWithCommission.setTotalPrice(correspondingestimatePricing.getTotalPrice() +	estimate.getCommission() -  estimate.getDiscountValue());
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
			EstimatePricing DiscountestimatePricing = new EstimatePricing();
			for (EstimatePricing invoicedEstimatePricing : estimatePricing) {
				if(invoicedEstimatePricing.getQuantity() == qty) {
					DiscountestimatePricing = invoicedEstimatePricing;
				}
			}

			long index = DiscountestimatePricing.getInvoices().get(0).getId();
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
	    return createInvoiceDataPdf(reference);
	}	

	public String createInvoiceDataPdf( String reference) throws IOException{
	 try {
		PdfWriter pdfWriter = new PdfWriter(invoiceDir+reference+ ".pdf");
		Invoice invoice=invoiceServiceImpl.findByReferencenumber(reference).get();
		JobEstimate jobEstimate = invoice.getEstimatePricing().getJobEstimate();
		Job job=jobEstimate.getJob();
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		Document document = new Document(pdfDocument, PageSize.A4);
	
//		 document.setMargins(25, 25, 297-156, 50);

		PrintableElement printer = new PrintableElement();	
		JobActivity jobActivity = job.getJobActivity();
		List<JobPaper> jcc = job.getJobPapers();
		JobPaper coverPaper = null;
		for(JobPaper pp : jcc) {
			if(pp.getContentType().getId()==1)
			{
				coverPaper=pp;
				printer.print(document,"Cover: "+ job.getOpenLength()+" X "+job.getOpenWidth()+" mm", 73, 297-93);
				printer.print(document, "Cover: " +job.getCoverVolume()+" Pages", 73, 297-110);
				printer.print(document,"Cover: "+  coverPaper.getJobColorCombinations().get(0).getFrontColorNumber() + "/" +coverPaper.getJobColorCombinations().get(0).getBackColorNumber()+" " + coverPaper.getJobColorCombinations().get(0).getPrintType().getName(), 73, 297-136);
				printer.printHeader(document,"Paper", 38, 297-182);
			    printer.print(document, "Cover : "+ job.getJobPapers().get(0).getPaperType().getName(), 73, 297-182);
				printer.print(document, job.getJobPapers().get(0).getGrammage()+" GSM", 168, 297-182);
		}}
		String jobActivities = "";
		String typeSettings = "";
		String reproduction = "";
			
			printer.printHeader(document, "Invoice ".toUpperCase() , 73, 297-42);
		 	printer.print(document, "("+invoice.getReferenceNumber().toUpperCase()+")", 97, 297-42);

		 	printer.printHeader(document,job.getCustomer().getName().toUpperCase(), 123, 297-52);
			printer.printHeader(document,job.getCustomer().getAddress().toUpperCase(), 123, 297-58);

			printer.printHeader(document, "Description", 38, 297-83);
			printer.print(document, job.getJobType().getName(), 73, 297-83);

			if(job.isTypesettingByUs()||job.isLayOutByUs()) typeSettings =	typeSettings + "By us,";
			if(job.isExistingPlate()) {
				reproduction = reproduction +  "Existing Plate";
				
			}else reproduction = reproduction +  "Data supplied By Customer";

			printer.printHeader(document, "Typesetting ", 38, 297-123);
			printer.print(document, typeSettings, 73, 297-123);
			
			printer.printHeader(document, " Reproduction", 38, 297-128 );
			printer.print(document, reproduction, 73, 297-128);

			printer.printHeader(document, "Format", 38, 297-93);
			printer.printHeader(document, "Volume", 38, 297-110);
			
			printer.printHeader(document, "Printing", 38, 297-137);


				String message_ =" ";
				boolean isContent =false;
				for(JobPaper pp:jcc) {
				if(pp.getContentType().getId()!=1)
					{
					isContent=true;
					for(int j=0; j<pp.getJobColorCombinations().size(); j++) {
					message_+=  pp.getJobColorCombinations().get(j).getFrontColorNumber()+"/"+ pp.getJobColorCombinations().get(j).getBackColorNumber()+" "+pp.getJobColorCombinations().get(j).getPrintType().getName()+"";
				}}
				
			}
				if(isContent) {
					printer.print(document, message_, 90, 297-142);
					printer.print(document, "Content", 73, 297-142);
					printer.print(document,"Content: "+  job.getCloseLength()+" X "+ job.getCloseWidth()+ " mm", 73, 297-99);
					printer.print(document, "Content: " +job.getContentVolume()+" Pages", 73, 297-116);
				}
			
			printer.printHeader(document, "Finishing", 38, 297-161);
			
			if(jobActivity.isHandgather()) jobActivities =	jobActivities + "hand-gatherd, ";
			if(jobActivity.isSelloptaped()) jobActivities =	jobActivities + " Selloptaped, ";
			if(jobActivity.isSewn()) jobActivities =	jobActivities + " Sewn,";
			if(jobActivity.isTrimmed()) jobActivities =	jobActivities + " trimmed, ";
			if(jobActivity.getIsStitching()!=null) jobActivities =	jobActivity.getIsStitching() + ", ";
			//if(!jobActivity.getGlueOption().isEmpty()) jobActivities =	jobActivities + jobActivity.getGlueOption()+ ", ";
			if(jobActivity.getXWiredStiched()>0) jobActivities =	jobActivities + jobActivity.getXWiredStiched()+ " x Stiched, ";
			if(jobActivity.getXCreased()>0) jobActivities =	jobActivities + " Cover "+ jobActivity.getXCreased()+ " x creased, ";
			if(jobActivity.getXCross()>0) jobActivities =	jobActivities + jobActivity.getXCross()+ " x folded,";
			if(jobActivity.getXNumbered()>0) jobActivities =	jobActivities + jobActivity.getXNumbered()+ " x Numbered, ";
			if(jobActivity.getBindingType()!=null) jobActivities =	jobActivities + jobActivity.getBindingType().getName()+" ";
			if(jobActivity.getLamination()>0) jobActivities =	jobActivities +" Cover " + jobActivity.getLamination() + " side(s) laminated, ";

		    printer.printParagraphe(document,jobActivities, 73, 297-170);

		   if(isContent)			
			printer.printParagraphe(document,"Content : ", 73, 297-187);
			float vecto = -5;
			for(JobPaper pp:jcc) {
				if(pp.getContentType().getId()!=1) {
					vecto+=5;
					printer.print(document, pp.getPaperType().getName(),  90, 297-187-vecto);
					printer.print(document, pp.getGrammage()+" GSM",  168, 297-187-vecto);
				}

			}
			printer.printHeader(document, "Quantity",  38, 297-200);
			printer.printHeader(document, "Unit(XAF)",  132, 297-200);
			printer.printHeader(document, "Total(XAF)",  172, 297-200);
			
			EstimatePricing estimates =invoice.getEstimatePricing();
			float vect = -5;
			DateFormat date =  DateFormat.getDateInstance(DateFormat.DEFAULT,Locale.ENGLISH);
			printer.printHeader(document,date.format(new Date())+"", 38, 297-73);
			
				vect+=5;
				printer.printMoney(document,estimates.getQuantity(), 82, 297-207-vect);
				printer.printMoney(document,estimates.getUnitPrice(), 132, 297-207-vect);
				printer.printMoney(document,estimates.getTotalPrice() , 171, 297-207-vect);
				

			double discountAmount = 0;
			if(invoice.getDiscountPercentage()> 0) {
				printer.printHeader(document, invoice.getDiscountPercentage()+" % Discount",  38, 270-200-vect);
				discountAmount = (invoice.getDiscountPercentage()/100) * invoice.getEstimatePricing().getTotalPrice();
				printer.printMoney(document,discountAmount,   171, 270-200-vect);
			}

			printer.print(document,"Cover: "+ job.getOpenLength()+" X "+job.getOpenWidth()+" mm", 73, 297-93);
			
			double vatValue = 0;
			if(invoice.getVatPercentage()> 0) {
				vatValue = (invoice.getVatPercentage()/100)*invoice.getEstimatePricing().getTotalPrice();
				printer.printHeader(document, invoice.getVatPercentage()+" % TVA",  38, 280-200-vect);
				printer.printMoney(document,vatValue, 171, 280-200-vect);
			}
			
			double irTaxValue = 0;
			if(invoice.getIrTaxPercentage()> 0)
			{
				irTaxValue = (invoice.getIrTaxPercentage()/100)*invoice.getEstimatePricing().getTotalPrice();
				printer.printHeader(document, invoice.getIrTaxPercentage()+" % IR Tax",  38, 275-200-vect);
				printer.printMoney(document,irTaxValue , 171, 275-200-vect);
			}
			
			printer.print(document, " --------------------",  171, 267-200-vect);
			
			printer.printHeader(document, " NetPayable",  38, 264-200-vect);
			printer.printMoney(document, invoice.getNetPayable(),  171, 264-200-vect);

			printer.print(document, " --------------------",  171, 261-200-vect);
			printer.print(document, " --------------------",  171, 260-200-vect);

			document.close();
				String	file = invoice.getReferenceNumber()+".pdf";
		 return "file="+file+"&dir=folder.invoice";
		} 
		
		catch (Exception e) {
			e.printStackTrace();
			return "file=error.pdf&dir=folder.invoice";
		}
		
	
	}
}
