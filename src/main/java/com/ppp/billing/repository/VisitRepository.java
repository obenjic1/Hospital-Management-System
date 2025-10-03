package com.ppp.billing.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ppp.billing.model.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByPatientId(Long patientId);
    
    @Query("SELECT s.name, COUNT(v), SUM(s.price) " +
    	       "FROM ConsultationSubtype s " +
    	       "JOIN s.visits v " +
    	       "WHERE v.visitDate BETWEEN :startDate AND :endDate " +
    	       "GROUP BY s.name")
    	List<Object[]> findSubtypesByDate(@Param("startDate") LocalDate startDate,
    	                                  @Param("endDate") LocalDate endDate);

    	
    	@Query("SELECT v.id, v.visitDate, s.name, s.price " +
    		       "FROM Visit v JOIN v.subtypes s " +
    		       "WHERE v.visitDate BETWEEN :startDate AND :endDate")
    		List<Object[]> findVisitsWithSubtypes(@Param("startDate") LocalDate startDate,
    		                                      @Param("endDate") LocalDate endDate);


    		@Query("SELECT v.attendingStaff.id, v.attendingStaff.name, COUNT(v) " +
    		           "FROM Visit v " +
    		           "WHERE v.visitDate BETWEEN :startDate AND :endDate " +
    		           "GROUP BY v.attendingStaff.id, v.attendingStaff.name " +
    		           "ORDER BY COUNT(v) DESC")
    		    List<Object[]> findMostActiveDoctors(@Param("startDate") LocalDate startDate,
    		                                         @Param("endDate") LocalDate endDate);
    		    @Query("SELECT v.attendingStaff.id, v.attendingStaff.firstName, v.attendingStaff.lastName, " +
    		    	       "       s.name, COUNT(s.id), SUM(s.price), v.attendingStaff.percentage " +
    		    	       "FROM Visit v " +
    		    	       "JOIN v.subtypes s " +
    		    	       "WHERE v.visitDate BETWEEN :startDate AND :endDate " +
    		    	       "GROUP BY v.attendingStaff.id, v.attendingStaff.firstName, v.attendingStaff.lastName, s.name, v.attendingStaff.percentage")
    		    	List<Object[]> findDoctorConsultationsByDate(@Param("startDate") LocalDate startDate,
    		    	                                             @Param("endDate") LocalDate endDate);




}