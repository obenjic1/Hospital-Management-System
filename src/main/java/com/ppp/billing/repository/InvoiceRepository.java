package com.ppp.billing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppp.billing.model.Invoice;
import java.util.List;
import java.util.Date;


@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
	//Optional<Invoice>  findByReferenceNumber (String referenceNumber);
	Optional<Invoice>  findById(long id );
	List<Invoice> findByCreationDateBetween(Date startDate, Date endDate);
}
