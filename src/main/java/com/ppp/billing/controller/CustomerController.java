package com.ppp.billing.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppp.billing.model.Customer;
import com.ppp.billing.model.dto.CustomerDTO;
import com.ppp.billing.serviceImpl.CustomerServiceImpl;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
//<------------------- Injection of dependencies  -------------------->
	@Autowired
	private CustomerServiceImpl customerServiceImpl;

//<------------------- Show registration form -------------------->
	@GetMapping("/displayCustomerForm")
	public String showRegistrationForm(Model model) {
		model.addAttribute("CustomerDTO", new CustomerDTO());
		
		return "billing/customer";
	}

//<------------------- Save customer -------------------->	
	@PostMapping("/save")
	public String save(CustomerDTO customerDTO) {
		Customer customer = customerServiceImpl.save(customerDTO);
		
		return "/billing/list-customer";
		
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Customer> customers = customerServiceImpl.findAll();
		model.addAttribute("customers", customers);
		
		return "/billing/list-customer";
	}

}
