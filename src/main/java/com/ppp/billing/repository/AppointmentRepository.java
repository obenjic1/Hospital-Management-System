package com.ppp.billing.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppp.billing.model.Appointment;
import com.ppp.billing.model.AppointmentStatus;
import com.ppp.billing.model.Patient;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate date);
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByStatus(AppointmentStatus status);
	List<Appointment> findByPatient(Patient patient);
    List<Appointment> findByappointmentDateBetween(LocalDateTime start, LocalDateTime end);

}
