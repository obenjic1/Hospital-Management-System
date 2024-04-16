package com.ppp.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.user.model.Printing;

public interface PrintingRepository extends JpaRepository<Printing, Long> {

}
