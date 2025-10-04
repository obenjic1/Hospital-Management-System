package com.ppp.billing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ppp.billing.model.Patient;
@Repository
public interface PatientRepository  extends JpaRepository<Patient, Long>{
    List<Patient> findByNameContainingIgnoreCase(String name);
    Optional<Patient> findFirstByNameContainingIgnoreCase(String name);
    
    @Query("SELECT COUNT(p) FROM Patient p")
    Long getTotalPatients();

    // New patients registered this month
    @Query("SELECT COUNT(p) FROM Patient p " +
           "WHERE MONTH(p.createdAt) = MONTH(CURRENT_DATE) " +
           "AND YEAR(p.createdAt) = YEAR(CURRENT_DATE)")
    Long getNewPatientsThisMonth();
    

}
