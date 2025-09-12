package com.ppp.billing.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ppp.billing.Dto.DoctorRevenueDTO;
import com.ppp.billing.model.Appointment;
import com.ppp.billing.model.Consultation;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findByAppointmentDoctorId(Long doctorId);
    Optional<Consultation> findByAppointmentId(Long appointmentId);
    List<Consultation> findByAppointmentIn(List<Appointment> appointments);
    List<Consultation> findByConsultationDateBetween(LocalDateTime start, LocalDateTime end);
    
 // Fetch consultations for a given month and year
    @Query("SELECT c FROM Consultation c WHERE MONTH(c.consultationDate) = :month AND YEAR(c.consultationDate) = :year")
    List<Consultation> findAllByMonthAndYear(@Param("month") int month, @Param("year") int year);

    // Grouped by doctor (return doctor + count)
    @Query("SELECT c.doctor.firstName, COUNT(c) FROM Consultation c " +
           "WHERE MONTH(c.consultationDate) = :month AND YEAR(c.consultationDate) = :year " +
           "GROUP BY c.doctor.firstName")
    List<Object[]> countConsultationsByDoctor(@Param("month") int month, @Param("year") int year);
    

    @Query("SELECT new com.ppp.billing.Dto.DoctorRevenueDTO(" +
    	       "CONCAT(c.doctor.firstName, ' ', c.doctor.lastName), " +    	
    		   "COUNT(c), " +
    	       "SUM(c.amountPaid), " +
    	       "c.doctor.percentage) " +
    	       "FROM Consultation c " +
    	       "WHERE FUNCTION('MONTH', c.consultationDate) = :month " +
    	       "AND FUNCTION('YEAR', c.consultationDate) = :year " +
    	       "GROUP BY c.doctor.firstName, c.doctor.lastName, c.doctor.percentage")    
    			List<DoctorRevenueDTO> getDoctorRevenueStatsByMonth(@Param("month") int month, @Param("year") int year);
    
	int countByconsultationDate(LocalDate now);
	int countByConsultationDateBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

}
