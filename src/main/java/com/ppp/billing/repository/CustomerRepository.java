package com.ppp.billing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppp.billing.model.Customer;
import com.ppp.user.model.User;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Page<Customer> findAll(Pageable pageable);
	Customer findByEmail(String email);
	Customer findByName(String name);

}
