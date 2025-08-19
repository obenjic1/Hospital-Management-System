package com.ppp.billing.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppp.billing.model.Customer;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	Page<Customer> findAll(Pageable pageable);
	Customer findByEmail(String email);
	Optional<Customer> findById(int id );
	Customer findByName(String name);
	

}
