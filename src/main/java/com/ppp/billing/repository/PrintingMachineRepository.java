package com.ppp.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ppp.billing.model.PrintingMachine;


public interface PrintingMachineRepository extends JpaRepository<PrintingMachine,Long> {

}
