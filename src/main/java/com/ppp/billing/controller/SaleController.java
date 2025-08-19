package com.ppp.billing.controller;


import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.io.IOException;
import com.ppp.billing.Dto.CartItemDto;
import com.ppp.billing.Dto.CheckoutResponse;
import com.ppp.billing.model.Sale;
import com.ppp.billing.service.PdfService;
import com.ppp.billing.service.SalesService;

@RestController
@RequestMapping("/sales")
public class SaleController {
	
	
		@Autowired
		
		private SalesService saleService;
		private PdfService pdfService;
	 
	 

	 


	    @PostMapping("/checkout")
	    public ResponseEntity<?> checkout(@RequestBody List<CartItemDto> saleDto) {
	    	
	    
	    	 Sale sale = saleService.processSale(saleDto,"oben","cash");
	    	 CheckoutResponse response = new CheckoutResponse();
	    	 response.setCashierName(sale.getPharmacist().getFirstName());
	    	 response.setId(sale.getId());
	    	 response.setCustomerName(sale.getCustomerName());
	    	 response.setReceiptNumber(sale.getReceiptNumber());
	    	 response.setTotal(sale.getTotal().toString());
	    	 response.setStatus("ok");
	    	    return ResponseEntity.ok(response);
	    }
	    
	    
	    
	    
	    @GetMapping("/receipt/{saleId}/pdf")
	    public ResponseEntity<byte[]> generateReceipt(@PathVariable Long saleId) throws IOException, FileNotFoundException {
	        Sale sale = saleService.findById(saleId);
	       

	        // generate PDF to memory
	 //       ByteArrayOutputStream out = new ByteArrayOutputStream();
	 //       pdfService.generateReceipt(sale); 
	        byte[] pdfContent = pdfService.generateReceipt(sale);
	        // modify generator to write to OutputStream

	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=receipt.pdf")
	                .contentType(MediaType.APPLICATION_PDF)	    
	                .body(pdfContent);
	       

        
	    }
}
