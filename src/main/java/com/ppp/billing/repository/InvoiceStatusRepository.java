package com.ppp.billing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.InvoiceStatus;
import com.ppp.billing.model.JobStatus;

public interface InvoiceStatusRepository extends JpaRepository<InvoiceStatus, Long> {
	
	Optional<InvoiceStatus>  findById(long id );
	Optional<InvoiceStatus>  findByName(String name );
//	JobStatus update( JobStatus jobStatus,  long id);
	
}