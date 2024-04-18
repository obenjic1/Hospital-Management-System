package com.ppp.billing.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.Customer;
import com.ppp.billing.model.dto.CustomerDTO;

@Service
public interface CustomerService {
	
	Customer save(CustomerDTO cutomerDTO);
	Customer update(CustomerDTO cutomerDTO, long id);
	void delete(long id);
	List<Customer> findAll();
	Optional<Customer> findByEmail(String email);

	
}
