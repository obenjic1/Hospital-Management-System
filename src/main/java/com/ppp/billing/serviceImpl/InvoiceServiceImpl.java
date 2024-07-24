package com.ppp.billing.serviceImpl;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import com.ppp.billing.model.JobPaper;
import com.ppp.billing.repository.EstimatePricingRepository;
import com.ppp.billing.repository.InvoiceRepository;
import com.ppp.billing.service.Invoiceservice;
import com.ppp.printable.PrintableElement;

@Service
public class InvoiceServiceImpl implements Invoiceservice{

	@Value("${folder.invoice}")
	private String invoiceDir;
	
	@Autowired
	private EstimatePricingRepository estimatePricingRepository;
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Override
	public Invoice findById(long id) {
		Invoice invoice = invoiceRepository.findById(id).get();
		return  invoice;
	}
	
	
	public Invoice saveInvoice(long id) {
		EstimatePricing estimate = estimatePricingRepository.findById(id).get();
		Invoice invoiceToSave = new Invoice();
		invoiceToSave.setEstimatePricingid(estimate);
		invoiceToSave.setCreationDate(new Date());
		/*
		 * Create Reference for Invoice
		 */
		String updateRef = estimate.getJobEstimate().getReference();
		String reference = updateRef.replace('E', 'I');	
		String invoiceRefence = reference.substring(0, reference.length()-1);
		invoiceRefence = invoiceRefence +""+(estimate.getInvoices().size()+1);
		invoiceToSave.setReferenceNumber(invoiceRefence);
		/*
		 * Calculate Net Payable
		 */
		double irTaxValue = (estimate.getJobEstimate().getIrTax()*estimate.getTotalPrice())/100;
		invoiceToSave.setIrTaxValue(irTaxValue);
		double tva = 0.0;
		if(estimate.getJobEstimate().isTva()) 	{
			 tva = 19.5;
		}
		double	tvaValue = (tva*estimate.getTotalPrice())/100;
		invoiceToSave.setTvaValue(tvaValue);
		invoiceToSave.setNetPayable(estimate.getTotalPrice()-tvaValue);
		return invoiceRepository.save(invoiceToSave);
	}



	@Override
	public List<Invoice> listInvoice() {
		
		return invoiceRepository.findAll();
	}

	@Override
	public String printInvoice(long id, String invoice) {
		try {
			PdfWriter pdfWriter = new PdfWriter(invoiceDir+invoice+".pdf");
			Invoice estimateInvoice=invoiceRepository.findById(id).get();
			Job job=estimateInvoice.getEstimatePricingid().getJobEstimate().getJob();
			PdfDocument pdfDocument = new PdfDocument(pdfWriter);
			Document document = new Document(pdfDocument, PageSize.A4);
			 document.setMargins(25, 25, 297-156, 50);
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
			
		
				
				printer.printHeader(document, "Estimate ".toUpperCase() , 73, 297-42);
			 	printer.print(document, "("+estimateInvoice.getReferenceNumber().toUpperCase()+")", 97, 297-42);

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
				if(jobActivity.isStitching()) jobActivities =	jobActivities + " Stitched, ";
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
				EstimatePricing estimates = estimateInvoice.getEstimatePricingid();
				float vect = -5;
				DateFormat date =  DateFormat.getDateInstance(DateFormat.DEFAULT,Locale.ENGLISH);
				
				printer.printHeader(document,date.format(new Date())+"", 38, 297-73);
					vect+=5;
					printer.printMoney(document,estimates.getQuantity(), 82, 297-207-vect);
					printer.printMoney(document,estimates.getUnitPrice(), 132, 297-207-vect);
					printer.printMoney(document,estimates.getTotalPrice() , 171, 297-207-vect);
					
					printer.printHeader(document, "19.25% VAT",  38, 297-197);
					printer.printHeader(document, "Tax IR 5.5%",  38, 297-201);
					printer.printHeader(document, "Net Payable",  38, 297-227);
					
					for(int i=0; i<estimates.getInvoices().size(); i++) {
						vect+=5;
						printer.printMoney(document,estimates.getInvoices().get(i).getTvaValue(), 82, 297-207-vect);
	//					printer.printMoney(document,estimates.get(i).getUnitPrice(), 132, 297-207-vect);
	//					printer.printMoney(document,estimates.get(i).getTotalPrice() , 171, 297-207-vect);
						
					}
			//		printer.printHeader(document, estimates,  168, 297-197);
					printer.printHeader(document, "Tax IR 5.5%",  168, 297-201);
					printer.printHeader(document, "Net Payable",  168, 297-227);

				document.close();
			String	file = estimateInvoice.getReferenceNumber()+".pdf";
			 return "file="+file+"&dir=folder.invoice";
			} 
						catch (Exception e) {
				e.printStackTrace();
				return "file=error.pdf&dir=folder.invoice";
			}
			
		}
	
	
}
