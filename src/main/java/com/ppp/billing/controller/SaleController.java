package com.ppp.billing.controller;


import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ppp.billing.Dto.CheckoutRequest;
import com.ppp.billing.Dto.CheckoutResponse;
import com.ppp.billing.model.Sale;
import com.ppp.billing.service.PdfService;
import com.ppp.billing.service.SalesService;

@RestController
@RequestMapping("/sales")
public class SaleController {
	
	
		@Autowired
		private SalesService saleService;
	 
		@Autowired
		private PdfService pdfService;;
	 

	 


	    @PostMapping("/checkout")
	    public ResponseEntity<?> checkout(@RequestBody CheckoutRequest saleDto) {
	    
	    	
	    try {
	    	Sale sale = saleService.processSale(saleDto);
	    	 CheckoutResponse response = new CheckoutResponse();
	    	 response.setCashierName(sale.getPharmacist().getFirstName());
	    	 response.setId(sale.getId());
	    	 response.setCustomerName(sale.getCustomerName());
	    	 response.setReceiptNumber(sale.getReceiptNumber());
	    	 response.setTotal(sale.getTotal().toString());
	    	 response.setStatus("ok");
	    	    return ResponseEntity.ok(response);	
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			 	}
	    	
	    }
	    
	    
	   
	    
	    @GetMapping("/receipt/{saleId}/pdf")
	    @ResponseBody
	    public ResponseEntity<FileSystemResource> generateReceipt(@PathVariable Long saleId) throws FileNotFoundException  {
	     
		File pdfContent =	pdfService.SendBack(saleId);
		if(!pdfContent.exists()) {
			return ResponseEntity.notFound().build();
		}
		FileSystemResource fileResource = new FileSystemResource(pdfContent);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION," attachment; filename=" +pdfContent.getName()).body(fileResource);
	}
	
	  
	        
	   
}
