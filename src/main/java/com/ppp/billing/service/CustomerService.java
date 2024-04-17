package com.ppp.billing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.Customer;
import com.ppp.billing.model.dto.CustomerDTO;

@Service
public interface CustomerService {
	
	String addCustomer(CustomerDTO cutomerDTO);
	List< Customer > getAllCustomer();
	String updateCustomer(Customer updateCustomer, Long id);
	Customer findCustomerByName(String name);
	void deleteCustomer(Long id);
	
}
