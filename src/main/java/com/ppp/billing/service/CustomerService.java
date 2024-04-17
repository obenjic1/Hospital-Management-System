package com.ppp.billing.service;

import java.util.List;

import com.ppp.billing.model.Customer;
import com.ppp.billing.model.dto.CustomerDTO;


public interface CustomerService {
	
	String addCustomer(CustomerDTO cutomerDTO);
	List< Customer > getAllCustomer();
	String updateCustomer(Customer updateCustomer, Long id);
	Customer findCustomerByName(String name);
	void deleteCustomer(Long id);
	

}
