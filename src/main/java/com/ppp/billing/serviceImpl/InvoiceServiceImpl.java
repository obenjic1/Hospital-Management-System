package com.ppp.billing.serviceImpl;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
import com.ppp.billing.repository.EstimatePricingRepository;
import com.ppp.billing.repository.InvoiceRepository;
import com.ppp.billing.repository.JobEstimateRepository;
import com.ppp.billing.service.InvoiceService;
import com.ppp.printable.PrintableElement;

@Service
public class InvoiceServiceImpl implements InvoiceService{

	@Value("${folder.invoice}")
	private String invoiceDir;
	
	@Autowired
	private EstimatePricingRepository estimatePricingRepository;
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private JobEstimateRepository estimateRepository;
	
	@Override
	public Invoice findById(long id) {
		Invoice invoice = invoiceRepository.findById(id).get();
		return  invoice;
	}
	
	public Invoice saveInvoice(long id) {
		EstimatePricing estimate = estimatePricingRepository.findById(id).get();
		JobEstimate jobEstimate=estimate.getJobEstimate();
		Invoice invoiceToSave = new Invoice();
		estimate.setInvoiced(true);
		invoiceToSave.setCreationDate(new Date());
		/*
		 * Create Reference for Invoice
		 */
		String updateRef = estimate.getJobEstimate().getReference();
		String reference = updateRef.replace('E', 'I');	
		String invoiceRefence = reference.substring(0, reference.length()-1);
		invoiceRefence = invoiceRefence  + ""+(estimate.getInvoices().size()+1);
		invoiceToSave.setReferenceNumber(invoiceRefence);
		/*
		 * Calculate Net Payable
		 */
	

		invoiceToSave.setNetPayable(estimate.getTotalPrice());
		estimate.getJobEstimate().setInvoiced(true);
		invoiceToSave.setEstimatePricing(estimate);
		
		/*
		 * Calculate  Discount
		 */
		
		
		jobEstimate.setEstimatePricings(jobEstimate.getEstimatePricings());
		invoiceRepository.saveAndFlush(invoiceToSave);
		estimate.setInvoices(estimate.getInvoices());
		estimate.setJobEstimate(jobEstimate);
		generateSerialNumber(invoiceToSave);
		return invoiceToSave;
	}

	@Override
	public List<Invoice> listInvoice() {
		return invoiceRepository.findAll();
	}

	@Override
	public Job getJobEstimateInvoice(long id) {
		Invoice invoice = invoiceRepository.findById(id).get();
		EstimatePricing estimatePricing = invoice.getEstimatePricing();
		JobEstimate estimate = estimatePricing.getJobEstimate();
		Job job = estimate.getJob();
		return job;
	}

/*
 * Find by date
 */
	@Override
	public List<Invoice> findByCreationDateBetwen(Date startDate, Date endDate) {
		try {
			return invoiceRepository.findByCreationDateBetween(startDate, endDate);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public Invoice setIrtaxAndVatTax(long id, double irTax, double vatTax) {
		try {
			Invoice invoice = invoiceRepository.findById(id).get();
			invoice.setIrTaxPercentage(irTax);
			invoice.setVatPercentage(vatTax);
			double irTaxValue =(irTax/100)*invoice.getEstimatePricing().getTotalPrice();
			double vatTaxValue =(vatTax/100)*invoice.getEstimatePricing().getTotalPrice();
			invoice.setNetPayable(irTaxValue+vatTaxValue+invoice.getEstimatePricing().getTotalPrice());
			invoiceRepository.save(invoice);
			return invoice;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	

	public void generateSerialNumber(Invoice invoice) {
		int currentCount = (int) (invoice.getId()%9999); 
		String countString = String.format("%06d", currentCount);
		String serialNuber = "I" + countString;
		invoice.setReferenceNumber(serialNuber);
		invoiceRepository.save(invoice);
	}
	
	
	@Override
	public Invoice applyDiscount(long id, double discount) {
		try {
			Invoice invoice = invoiceRepository.findById(id).get();
			invoice.setDiscountPercentage(discount);
			invoice.setNetPayable((invoice.getEstimatePricing().getTotalPrice())-((discount/100)*(invoice.getEstimatePricing().getTotalPrice())));
			return invoiceRepository.save(invoice);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Invoice displayIrtaxAndVatTax(long id, double irTax, double vatTax) {
		try {
		Invoice invoice = invoiceRepository.findById(id).get();
		invoice.setIrTaxPercentage(irTax);
		invoice.setVatPercentage(vatTax);
	
		invoiceRepository.save(invoice);
		return invoice;
	} catch (Exception e) {
		throw e;
	}
	}

	@Override
	public Optional<Invoice> findByReferencenumber(String referenceNumber) {
		try {
			Optional<Invoice> invoice = invoiceRepository.findByReferenceNumber(referenceNumber);
			if(invoice.isPresent())
				invoice.get();
			return invoice;
		} catch (Exception e) {
			throw e;
		}
	}

	public Invoice applyDiscountAmount(long id, double discountAmount) {
		try {
			Invoice invoice = invoiceRepository.findById(id).get();
			double discount = (discountAmount/invoice.getEstimatePricing().getTotalPrice())*100;
			invoice.setDiscountPercentage(discount);
			invoice.setNetPayable((invoice.getEstimatePricing().getTotalPrice())-((discount/100)*(invoice.getEstimatePricing().getTotalPrice())));
			return invoiceRepository.save(invoice);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public Invoice saveInvoiceWithDiscount(long id, int qty) {
		JobEstimate estimate = estimateRepository.findById(id).get();
		List<EstimatePricing> estimatePricing = estimate.getEstimatePricings();
		Invoice invoiceToSave = new Invoice();
		EstimatePricing selectedPricingElement = new EstimatePricing();
		estimate.setInvoiced(true);
		invoiceToSave.setCreationDate(new Date()); 
		for(EstimatePricing correspondingestimatePricing : estimatePricing ) {
			if(correspondingestimatePricing.getQuantity() == qty) {
				
				correspondingestimatePricing.setQuantity(qty);
				correspondingestimatePricing.setTotalPrice(correspondingestimatePricing.getTotalPrice());
				correspondingestimatePricing.setUnitPrice(correspondingestimatePricing.getTotalPrice()/correspondingestimatePricing.getQuantity());
				correspondingestimatePricing.setInvoiced(true);
				selectedPricingElement = correspondingestimatePricing;
			}
			
		}
			/*
			 * Create Reference for Invoice
			 */
			String updateRef = estimate.getReference();
			String reference = updateRef.replace('E', 'I');	
			String invoiceRefence = reference.substring(0, reference.length()-1);
			invoiceRefence = invoiceRefence  + ""+(selectedPricingElement.getInvoices().size()+1);
			invoiceToSave.setReferenceNumber(invoiceRefence);
			/*
			 * Calculate Net Payable
			 */
		

			invoiceToSave.setNetPayable(selectedPricingElement.getTotalPrice());
			invoiceToSave.setEstimatePricing(selectedPricingElement);
			
			/*
			 * Calculate  Discount
			 */
		
			estimate.setEstimatePricings(estimate.getEstimatePricings());
			invoiceRepository.saveAndFlush(invoiceToSave);
			selectedPricingElement.setInvoices(selectedPricingElement.getInvoices());
			selectedPricingElement.setJobEstimate(estimate);
			generateSerialNumber(invoiceToSave);
			
		return invoiceToSave;
	}
	
	public String createInvoiceDataPdf( String reference) throws IOException{
		 try {
			PdfWriter pdfWriter = new PdfWriter(invoiceDir+reference+ ".pdf");
			Invoice invoice=invoiceRepository.findByReferenceNumber(reference).get();
			JobEstimate jobEstimate = invoice.getEstimatePricing().getJobEstimate();
			Job job=jobEstimate.getJob();
			PdfDocument pdfDocument = new PdfDocument(pdfWriter);
			Document document = new Document(pdfDocument, PageSize.A4);
		
//			 document.setMargins(25, 25, 297-156, 50);

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
						if(job.getJobType().getCategory()!=3)
							 printer.print(document, "Content: " +job.getContentVolume()+" Pages", 73, 297-116);
						else 
							printer.print(document, "Content: " +job.getContentVolume()+ " x "+ job.getCardCopies() +" Copies", 73, 297-116);
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
