package com.ppp.billing.service;



import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ppp.billing.model.Customer;
import com.ppp.billing.model.dto.CustomerDTO;

@Service
public interface CustomerService {
	
	Customer save(CustomerDTO cutomerDTO);
	Page<Customer> pagination(int pageNo, int pageSize);
	Customer findByEmail(String email);
	List<Customer> findAll();
//	Customer update(CustomerDTO customerDTO, Long id);
	File downloadFile(String fileName, String fileStoragePath);
	void delete(long id);

	
}
