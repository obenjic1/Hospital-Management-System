package com.ppp.billing.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.Invoice;

@Service
public interface InvoiceService {

	Optional<Invoice> findByReferencenumber (String referenceNumber);
	Invoice findById (long id);
//    String printInvoice(long id, String invoice);
    List<Invoice> listInvoice();
   
    List<Invoice> findByCreationDateBetwen(Date startDate, Date endDate);
    Invoice setIrtaxAndVatTax(long id, double irTax, double vatTax);
    Invoice displayIrtaxAndVatTax(long id, double irTax, double vatTax);
	Invoice applyDiscount(long id, double discount);
	Invoice applyDiscountAmount(long id, double discount);

//	Invoice save (long id);
}
