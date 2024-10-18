package com.ppp.billing.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.ppp.billing.model.EstimatePricing;
import com.ppp.billing.model.Invoice;
import com.ppp.billing.model.Job;
import com.ppp.billing.model.JobEstimate;
import com.ppp.billing.repository.EstimatePricingRepository;
import com.ppp.billing.repository.InvoiceRepository;
import com.ppp.billing.repository.JobEstimateRepository;
import com.ppp.billing.service.InvoiceService;

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
	
}
