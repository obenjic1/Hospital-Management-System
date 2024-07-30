package com.ppp.billing.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.Customer;
import com.ppp.billing.model.Job;



public interface JobRepository extends JpaRepository<Job, Long> {

	Optional<Job>  findById(long id );
	List<Job> findByCustomer(Customer customer);
	Optional<Job>  deleteById(long id );
	Optional<Job> findByReferenceNumber(String refrenceNumber);
	List<Job> findByCreationDateBetween(Date startDate, Date endDate);
}
