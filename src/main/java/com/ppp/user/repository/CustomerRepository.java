package com.ppp.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.user.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
