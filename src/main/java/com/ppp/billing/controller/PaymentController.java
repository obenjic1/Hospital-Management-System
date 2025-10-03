package com.ppp.billing.controller;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.io.IOException;
import com.ppp.billing.Dto.PaymentDTO;
import com.ppp.billing.model.Payment;
import com.ppp.billing.service.PaymentService;
import com.ppp.billing.service.PdfService;



@Controller
@RequestMapping("payments")
public class PaymentController {
	
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private PdfService pdfService;
	
	
	@PostMapping
	public ResponseEntity<?> pay(@RequestParam Long factureId,
	                                  @RequestParam BigDecimal amountPaid,
	                                  @RequestParam String paymentMethod
	                               ) throws java.io.IOException {

	    PaymentDTO dto = new PaymentDTO();
	    dto.setFactureId(factureId);
	    dto.setAmountPaid(amountPaid);
	   dto.setPaymentMethod(paymentMethod);

	    Payment p = paymentService.savePaymentFacture(dto);
	    return ResponseEntity.ok(p.getId());
	}
	
	@GetMapping("/receipt/{paymentId}")
	public void receipt(@PathVariable Long paymentId, HttpServletResponse resp) throws IOException, java.io.IOException {

	    Payment payment = paymentService.findById(paymentId);

	    String path = payment.getReceiptPath();
	    if (path == null || !Files.exists(Paths.get(path))) {
	        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Receipt not found");
	        return;
	    }

	    resp.setContentType("application/pdf");
	    resp.setHeader("Content-Disposition", "inline; filename=" + payment.getReference() + ".pdf");
	    Files.copy(Paths.get(path), resp.getOutputStream());
	    resp.getOutputStream().flush();
	}



}
