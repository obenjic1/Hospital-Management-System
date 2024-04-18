package com.ppp.billing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppp.billing.model.JobType;
import com.ppp.billing.model.PaperFormat;


@Repository
public interface PaperFormatRepository extends JpaRepository<PaperFormat, Long> {
	
	Optional<PaperFormat> findByName(String name);
	Optional<PaperFormat>  findById(long id );

}
