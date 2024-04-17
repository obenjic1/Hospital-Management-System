package com.ppp.billing.serviceImpl;

import java.util.Date;
import java.util.List;


import com.ppp.billing.model.Customer;
import com.ppp.billing.model.dto.CustomerDTO;
import com.ppp.billing.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {


//<---------------- Add customer ---------------------->
	@Override
	public String addCustomer(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setName(customerDTO.getName());
		customer.setEmail(customerDTO.getEmail());
		customer.setAddress(customerDTO.getAddress());
		customer.setTelephone(customerDTO.getTelephone());
		customer.setCreationDate(new Date());
		return null;
	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateCustomer(Customer updateCustomer, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findCustomerByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(Long id) {
		// TODO Auto-generated method stub
		
	}

	

}
