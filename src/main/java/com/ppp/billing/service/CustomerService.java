package com.ppp.billing.service;



import java.io.File;
import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ppp.billing.model.Customer;
import com.ppp.billing.model.dto.CustomerDTO;

@Service
public interface CustomerService {
	
	Customer save(CustomerDTO cutomerDTO);
	void delete(long id);
	Page<Customer> pagination(int pageNo, int pageSize);
	Customer findByEmail(String email);
	Customer update(CustomerDTO customerDTO, Long id);
	File downloadFile(String fileName, String fileStoragePath);

	
}
