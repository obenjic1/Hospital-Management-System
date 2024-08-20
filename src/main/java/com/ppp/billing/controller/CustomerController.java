package com.ppp.billing.controller;



import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ppp.billing.model.Customer;
import com.ppp.billing.model.dto.CustomerDTO;
import com.ppp.billing.serviceImpl.CustomerServiceImpl;


@Controller
@RequestMapping("/customer")
public class CustomerController {

//<------------------- Retrieve the value from application.properties  -------------------->	

	@Value("${paginationSise}")
	private int paginationSize;	
	
	@Value("${folder.storage.path}")
	private String customerFileStorage;
	
//	@Value("${page.initial.size}")
//	private int initialPage;
//	
	
	
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
	@ResponseBody
	public String save(CustomerDTO customerDTO) {
		
		try {
			Customer customer = customerServiceImpl.saveCustomer(customerDTO);		
			return "SUCCESS";	
			
		} catch (Exception e) {
			return "FAILED";
		}
		
	}
	
//<------------------- Customers -------------------->
	@PreAuthorize("hasAuthority('ROLE_LIST_CUSTOMER')")
	@GetMapping("/list")
	public String list(Model model) {
		//int firstPage = initialPage;
		return pagination(1, model);
	}
	
	@GetMapping("/page/{pageNo}")
	public String pagination(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = paginationSize;
		Page <Customer> customer = customerServiceImpl.pagination(pageNo, pageSize);
		 List < Customer > allCustomer = customer.getContent();
		 int totalElement = allCustomer.size();
		 model.addAttribute("currentPage", pageNo);
		 model.addAttribute("totalPages", customer.getTotalPages());
		 model.addAttribute("totalItems", customer.getTotalElements());
		 model.addAttribute("allCustomers", allCustomer);
		 model.addAttribute("totalElement", totalElement);
		 
		return "/billing/list-customer";
	}
	
//<------------------- Find by email -------------------->
	@PreAuthorize("hasAuthority('ROLE_VIEW_CUSTOMER_DETAILS')")
	@GetMapping("/find/{email}")
	public String findByEmail(@PathVariable String email, Model model) {
		Customer customer = customerServiceImpl.findByEmail(email);
		model.addAttribute("customer", customer);
		model.addAttribute("jobs", customer.getJobs());
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

	@PostMapping("/updateCustomer/{id}")
	public ResponseEntity<String> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable int id) {			
		try {
			Customer updatedCustomer = customerServiceImpl.update(customerDTO, id);	

			return new ResponseEntity<String>("Success", HttpStatus.OK);
		} catch (Exception e) {			
			return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
		}
	}

//<------------------- Delete customer -------------------->
	@PreAuthorize("hasAuthority('ROLE_DELETE_CUSTOMER')")
	@PostMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
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
	
	
	/*
	 * Process to create customer at the level of Job form
	 */
	@GetMapping("/customerForm")
	public String displayCustomerForm(Model model) {
		model.addAttribute("CustomerDTO", new CustomerDTO());
		return "billing/customer-job-form";
	}
	
	@PostMapping("/job/new-customer")
	@ResponseBody
	public Customer createCustomerAtTheJobForm(CustomerDTO customerDTO, Model model) throws Exception {
		try {
			return customerServiceImpl.saveCustomer(customerDTO);
		} catch (Exception e) {
			throw e;
		}
	}
	
	@GetMapping("/get/new-customer")
	public String listCustomerToJobForm(Model model) {
		try {
			List<Customer> customerResult = customerServiceImpl.findAll();
			Collections.reverse(customerResult);
			Customer customerSelected = customerResult.get(0);
			model.addAttribute("customerSelected", customerSelected);
			model.addAttribute("customers", customerResult);
			return "billing/create-customer-to-job-view";
		} catch (Exception e) {
			throw e;
		}
		
	}
	/*
	 * 
	 */
	
}
