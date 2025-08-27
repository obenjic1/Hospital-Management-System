package com.ppp.billing.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppp.billing.Dto.PaymentItemDto;
import com.ppp.billing.model.PaymentItem;
import com.ppp.billing.service.PatientService;
import com.ppp.billing.service.PaymentItemService;
import com.ppp.billing.service.PdfService;
import com.ppp.billing.service.ServiceItemService;

@Controller
@RequestMapping("/payments")
public class PaymentItemController {
	
    @Autowired

	private  PaymentItemService paymentService;
	@Autowired
	private PdfService pdfService;;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ServiceItemService serviceItemService;

    @GetMapping
    public String listPayments(Model model) {
        model.addAttribute("payments", paymentService.getAllPayments());
        return "payment/list-payment"; // JSP: /WEB-INF/views/payments/list.jsp
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
    	
       model.addAttribute("payment", new PaymentItem());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("services", serviceItemService.getAllServices());
        return "payment/createPayment"; 
    }

    
    @PostMapping("/create")
    @ResponseBody
    public PaymentItem createPayment(@RequestParam(required = false) Long patientId,
    								@RequestParam(required = false) String unregisteredPatientName,
                                 @RequestParam String serviceItemIds,
                                 @RequestParam (required = false)String paymentMethod) {
    	
    	String[]	names = unregisteredPatientName.split(",");
    	List<Long> ids = Arrays.stream(serviceItemIds.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    	PaymentItemDto payment = new PaymentItemDto();
    	if(patientId != null) {
    		payment.setPatientId(patientId);
    	} else {
    		payment.setUnreegisteredPatien(names[0]);
    	}
    	payment.setPaymentMethod(paymentMethod);
        return paymentService.createPayment(payment, ids);
    }

    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return "redirect:/payments";
    }

    @GetMapping("/{id}")
    public PaymentItem getPaymentById(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }
    
    @GetMapping("/receipt/{id}/pdf")
    @ResponseBody
    public ResponseEntity<FileSystemResource> generateReceipt(@PathVariable Long id) throws FileNotFoundException  {
     
	File pdfContent = pdfService.printRecept(id);
	if(!pdfContent.exists()) {
		return ResponseEntity.notFound().build();
	}
	FileSystemResource fileResource = new FileSystemResource(pdfContent);
	return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION," attachment; filename=" +pdfContent.getName()).body(fileResource);
}
    
    @PostMapping("/pay/{id}")
    public  ResponseEntity<String>  confirmPayment(@PathVariable Long id) {
    	paymentService.confirmPayment(id);
		return new ResponseEntity<>("Success", HttpStatus.OK);
    	
    }
    
    @GetMapping("/history/{id}")
    public String getHistory(@PathVariable Long id,Model model) {
        model.addAttribute("payment", paymentService.getPaymentById(id));
        return "payment/history";
    }
}
