package com.ppp.billing.serviceImpl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
//<---------------- List customers with pagination ---------------------->
	@Override
	public Page<Customer> pagination(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);		
		return customerRepostory.findAll(pageable);
	}
	
//<---------------- List customers ---------------------->	
	@Override
	public List<Customer> findAll() {
		return customerRepostory.findAll();
	}


//<---------------- Update ---------------------->
	@Override
	public Customer update(CustomerDTO customerDTO, int id) {
		Customer customer = customerRepostory.findById(id).get();
		customer.setName(customerDTO.getName());
		customer.setEmail(customerDTO.getEmail());
		customer.setAddress(customerDTO.getAddress());
		customer.setTelephone(customerDTO.getTelephone());
		customer.setCreationDate(customerDTO.getCreationDate());
		return customerRepostory.save(customer);
	}

//<---------------- Delete customer ---------------------->
	@Override
	public void delete(int id) {
		Customer delete = customerRepostory.findById(id).get();
		customerRepostory.delete(delete);
		
	}

//<---------------- Find by Email ---------------------->
	@Override
	public Customer findByEmail(String email) {		
		return customerRepostory.findByEmail(email);
	}

//<---------------- Save customer image ---------------------->
	@Override
    public File downloadFile(String fileName, String fileStoragePath) {
           String filePath = fileStoragePath + "/" + fileName;
           File file = new File(filePath);
           if (file.exists()) {
               return file;
           } else {
               return null;
           }
       }


}
