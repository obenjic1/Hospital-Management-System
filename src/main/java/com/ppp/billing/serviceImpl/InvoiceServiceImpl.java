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
import com.ppp.billing.model.Invoice;
import com.ppp.billing.model.InvoiceStatus;
import com.ppp.billing.model.JobEstimate;
import com.ppp.billing.repository.InvoiceRepository;
import com.ppp.billing.repository.InvoiceStatusRepository;
import com.ppp.billing.repository.JobEstimateRepository;
import com.ppp.billing.service.InvoiceService;
import com.ppp.printable.PrintableElement;

@Service
public class InvoiceServiceImpl implements InvoiceService{

	@Value("${folder.invoice}")
	private String invoiceDir;
	

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	
	
	@Autowired
	private InvoiceStatusRepository invoiceStatusRepository;
	
	@Override
	public Invoice findById(long id) {
		Invoice invoice = invoiceRepository.findById(id).get();
		return  invoice;
	}
	
	public String saveInvoice(long id) {
	
		
		InvoiceStatus status= invoiceStatusRepository.findByName("Registered").get();
	
		
		/*
		 * Calculate  Discount
		 */
		
		
		return "invoiceToSave";
	}

	@Override
	public List<Invoice> listInvoice() {
		return invoiceRepository.findAll();
	}

	public Invoice getJobEstimateInvoice(long id) {
		Invoice invoice = invoiceRepository.findById(id).get();
		
		return invoice;
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
//			double irTaxValue =(irTax/100)*invoice.getEstimatePricing().getTotalPrice();
//			double vatTaxValue =(vatTax/100)*invoice.getEstimatePricing().getTotalPrice();
//			invoice.setNetPayable(irTaxValue+vatTaxValue+invoice.getEstimatePricing().getTotalPrice());
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
			return invoiceRepository.save(invoice);
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public Invoice saveInvoiceWithDiscount(long id, int qty) {
		Invoice invoiceToSave = new Invoice();
		invoiceToSave.setCreationDate(new Date()); 
		
			/*
			 * Create Reference for Invoice
			 */
		
			/*
			 * Calculate  Discount
			 */
			
			generateSerialNumber(invoiceToSave);
			
		return invoiceToSave;
	}


	public String createInvoiceDataPdf( String reference, boolean isCommission, boolean isApplyTaxe) throws IOException{

		 try {
			PdfWriter pdfWriter = new PdfWriter(invoiceDir+reference+ ".pdf");
			Invoice invoice=invoiceRepository.findByReferenceNumber(reference).get();
			PdfDocument pdfDocument = new PdfDocument(pdfWriter);
			Document document = new Document(pdfDocument, PageSize.A4);
		
//			 document.setMargins(25, 25, 297-156, 50);

			PrintableElement printer = new PrintableElement();	
			
			String jobActivities = "";
			String typeSettings = "";
			String reproduction = "";
				
				
					reproduction = reproduction +  "Existing Plate";
					

				printer.printHeader(document, "Typesetting ", 38, 297-123);
				printer.print(document, typeSettings, 73, 297-123);
				
				printer.printHeader(document, " Reproduction", 38, 297-128 );
				printer.print(document, reproduction, 73, 297-128);

				printer.printHeader(document, "Format", 38, 297-93);
				printer.printHeader(document, "Volume", 38, 297-110);
				
				printer.printHeader(document, "Printing", 38, 297-137);


					String message_ =" ";
					boolean isContent =false;
					
					
				printer.printHeader(document, "Finishing", 38, 297-161);
				
				
			    printer.printParagraphe(document,jobActivities, 73, 297-170);

			   if(isContent)			
				printer.printParagraphe(document,"Content : ", 73, 297-187);
				float vecto = -5;
				
				printer.printHeader(document, "Quantity",  38, 297-200);
				printer.printHeader(document, "Unit(XAF)",  132, 297-200);
				printer.printHeader(document, "Total(XAF)",  172, 297-200);
				
				
				float vect = -5;
				DateFormat date =  DateFormat.getDateInstance(DateFormat.DEFAULT,Locale.ENGLISH);
				printer.printHeader(document,date.format(new Date())+"", 38, 297-73);
				
				

				double discountAmount = 0;
				if(invoice.getDiscountPercentage()> 0) {
					printer.printHeader(document, invoice.getDiscountPercentage()+" % Discount",  38, 270-200-vect);
					printer.printMoney(document,discountAmount,   171, 270-200-vect);
				}

				
				double vatValue = 0;
				if(invoice.getVatPercentage()> 0) {
					printer.printHeader(document, invoice.getVatPercentage()+" % TVA",  38, 280-200-vect);
					printer.printMoney(document,vatValue, 171, 280-200-vect);
				}
				
				double irTaxValue = 0;
				if(invoice.getIrTaxPercentage()> 0)
				{
					printer.printHeader(document, invoice.getIrTaxPercentage()+" % IR Tax",  38, 275-200-vect);
					printer.printMoney(document,irTaxValue , 171, 275-200-vect);
				}
				
				printer.print(document, " --------------------",  171, 267-200-vect);
				
				printer.printHeader(document, " NetPayable",  38, 264-200-vect);
				int netPayable=(int)invoice.getNetPayable();
				
				if(!isApplyTaxe) {
					netPayable=(int)(netPayable -irTaxValue-vatValue);
				}
			
				printer.printMoney(document, netPayable,  171, 264-200-vect);
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

	public void confirmInvoice(String referenceNumber) {
		Invoice invoice = invoiceRepository.findByReferenceNumber(referenceNumber).get();
		if(invoice.getInvoiceStatus().getName().equals("Registered")) {
			InvoiceStatus status = invoiceStatusRepository.findByName("Approved").get();
			invoice.setInvoiceStatus(status);
			
			invoiceRepository.save(invoice);
			
			
		}
		
	}
}
