package com.ppp.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.Customer;



public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
