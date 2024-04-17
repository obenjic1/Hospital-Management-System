package com.ppp.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.Printing;


public interface PrintingRepository extends JpaRepository<Printing, Long> {

}
