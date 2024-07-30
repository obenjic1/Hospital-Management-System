package com.ppp.billing.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.ppp.billing.model.EstimatePricing;
import com.ppp.billing.model.Invoice;
import com.ppp.billing.model.Job;
import com.ppp.billing.model.JobEstimate;
import com.ppp.billing.repository.EstimatePricingRepository;
import com.ppp.billing.repository.InvoiceRepository;
import com.ppp.billing.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService{

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
		estimate.setInvoiced(true);
		invoiceToSave.setEstimatePricingid(estimate);
		invoiceToSave.setCreationDate(new Date());
		estimate.getJobEstimate().setInvoiced(true);
		
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
	public Job getJobEstimateInvoice(long id) {
		Invoice invoice = invoiceRepository.findById(id).get();
		EstimatePricing estimatePricing = invoice.getEstimatePricingid();
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
	
	
}
