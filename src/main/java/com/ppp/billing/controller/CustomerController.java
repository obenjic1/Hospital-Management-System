package com.ppp.billing.controller;



import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppp.billing.model.Customer;
import com.ppp.billing.model.dto.CustomerDTO;
import com.ppp.billing.serviceImpl.CustomerServiceImpl;


@Controller
@RequestMapping("/customer")
public class CustomerController {

//<------------------- Retrieve the value from application.properties  -------------------->	
	@Value("${paginationSise}")
	private int paginationSize;	
	@Value("{file.storage;path}")
	private String customerFileStorage;
	
	
//<------------------- Injection of dependencies  -------------------->
	@Autowired
	private CustomerServiceImpl customerServiceImpl;

//<------------------- Show registration form -------------------->
	@PreAuthorize("hasAuthority('ROLE_SAVE_CUSTOMER')")
	@GetMapping("/displayCustomerForm")
	public String showRegistrationForm(Model model) {
		model.addAttribute("CustomerDTO", new CustomerDTO());
		
		return "billing/customer";
	}

//<------------------- Save customer -------------------->	
	@PostMapping("/save")
	public ResponseEntity<String> save(CustomerDTO customerDTO) {
		
		try {
			Customer customer = customerServiceImpl.save(customerDTO);
			return new ResponseEntity<String>("Success", HttpStatus.CREATED);
			
		} catch (Exception e) {
			Customer customer = customerServiceImpl.save(customerDTO);
			return new ResponseEntity<String>("failed", HttpStatus.ALREADY_REPORTED);
		}
		
	}
	
//<------------------- Customers -------------------->
	@PreAuthorize("hasAuthority('ROLE_LIST_CUSTOMER')")
	@GetMapping("/list")
	public String list(Model model) {	
		return pagination(1, model);
	}
	
	@GetMapping("/page/{pageNo}")
	public String pagination(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = paginationSize;
		Page <Customer> customer = customerServiceImpl.pagination(pageNo, pageSize);
		 List < Customer > allCustomer = customer.getContent();
		 model.addAttribute("currentPage", pageNo);
		 model.addAttribute("totalPages", customer.getTotalPages());
		 model.addAttribute("totalItems", customer.getTotalElements());
		 model.addAttribute("allCustomers", allCustomer);
		 
		return "/billing/list-customer";
	}
	
//<------------------- Find by email -------------------->
	@PreAuthorize("hasAuthority('ROLE_VIEW_CUSTOMER_DETAILS')")
	@GetMapping("/find/{email}")
	public String findByEmail(@PathVariable String email, Model model) {
		Customer customer = customerServiceImpl.findByEmail(email);
		model.addAttribute("customer", customer);
		return "/billing/customer-details";
	}
	
//<------------------- Find customer to update -------------------->
	@PreAuthorize("hasAuthority('ROLE_VIEW_CUSTOMER_DETAILS')")
	@GetMapping("/update/{email}")
	public String findCutomerByEmail(@PathVariable String email, Model model) {			
		Customer findCustomerToUpdate = customerServiceImpl.findByEmail(email);
		model.addAttribute("update", findCustomerToUpdate);
		
		return "/billing/update-customer";
	}


//<------------------- Update customer -------------------->
	@PreAuthorize("hasAuthority('ROLE_UPDATE_CUSTOMER')")
	@PostMapping("/updatecustomer/{id}")
	private ResponseEntity<String> update(@RequestBody CustomerDTO customerDTO, @PathVariable Long id) {			
		try {
			Customer updatedCustomer = customerServiceImpl.update(customerDTO, id);		
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} catch (Exception e) {			
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}

//<------------------- Delete customer -------------------->
	@PreAuthorize("hasAuthority('ROLE_DELETE_CUSTOMER')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(Long id) {
		try {
			customerServiceImpl.delete(id);
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}		
	}

//<------------------- Download customer image -------------------->	
	@GetMapping("/image/{fileName}")
	public void image(@PathVariable String fileName, HttpServletResponse response) throws IOException {
		File fileResource = customerServiceImpl.downloadFile(fileName, customerFileStorage);
		String mimeType = URLConnection.guessContentTypeFromName(fileResource.getName());
		response.setContentType(mimeType);
		response.setHeader("Content-Disposition", String.format("attachement; filename=\""+fileResource.getName()+"\""));
		InputStream inputStream = new BufferedInputStream(new FileInputStream(fileResource));
		FileCopyUtils.copy(inputStream, response.getOutputStream());
		inputStream.close();
	}
	
}
