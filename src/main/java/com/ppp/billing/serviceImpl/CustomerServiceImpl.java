package com.ppp.billing.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.Customer;
import com.ppp.billing.model.dto.CustomerDTO;
import com.ppp.billing.repository.CustomerRepository;
import com.ppp.billing.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepostory ;

//<---------------- Add customer ---------------------->
	@Override
	public Customer save(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setName(customerDTO.getName());
		customer.setEmail(customerDTO.getEmail());
		customer.setAddress(customerDTO.getAddress());
		customer.setTelephone(customerDTO.getTelephone());
		customer.setCreationDate(new Date());
		return customerRepostory.save(customer);
	}
	
//<---------------- List customers ---------------------->
	@Override
	public List<Customer> findAll() {
		return customerRepostory.findAll();
	}

	@Override
	public Customer update(CustomerDTO updateCustomerDto, long id) {
		// TODO Auto-generated method stub
		return customerRepostory.save(null) ;
	}

	@Override
	public Optional<Customer> findByEmail(String email) {
		return customerRepostory.findByEmail(email);
	}

	@Override
	public void delete(long id) {
		customerRepostory.deleteById(id);
		
	}

	

}
