package com.ppp.billing.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.Invoice;

@Service
public interface Invoiceservice {

	//Optional<Invoice> findByReferencenumber (String referenceNumber);
	Invoice findById (long id);
    String printInvoice(long id, String invoice);
    List<Invoice> listInvoice();

//	Invoice save (long id);
}
