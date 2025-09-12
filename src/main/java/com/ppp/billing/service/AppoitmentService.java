package com.ppp.billing.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.Dto.AppointmentDto;
import com.ppp.billing.model.Appointment;
import com.ppp.billing.model.AppointmentStatus;
import com.ppp.billing.model.Patient;
import com.ppp.billing.repository.AppointmentRepository;

@Service
public class AppoitmentService {
	@Autowired
    private AppointmentRepository appointmentRepository;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private PatientService patientService;

    public Appointment createAppointment(AppointmentDto appointment) {
    	Appointment appoint = new Appointment ();
//    	appoint.setStatus(AppointmentStatus.SCHEDULED);
    	
    	appoint.setDoctor(staffService.getStaffById(appointment.getDoctor_id()));
    	appoint.setAppointmentDate(appointment.getAppointmentDate());
    	appoint.setPatient(patientService.getPatientById(appointment.getPatientId()));
    	appoint.setStatus(AppointmentStatus.SCHEDULED);
    	appoint.setReason(appointment.getReason());
    	
    	Patient patient = patientService.getPatientById(appointment.getPatientId());
    			patient.addTracking("CREATE", "Added this patient");
    			patientService.createPatient(patient);
        return appointmentRepository.save(appoint);
    }

    public List<Appointment> getAppointmentsForDoctor(Long doctorId, LocalDate date) {
        return appointmentRepository.findByDoctorIdAndAppointmentDate(doctorId, date);
    }

    public void cancelAppointment(Long id) {
        Appointment appt = appointmentRepository.findById(id).orElseThrow(null);
        appt.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appt);
    }

	public Object getAllAppointments() {
		return appointmentRepository.findAll();
	}

	public List<Appointment> findByPatient(Patient patient) {
		return appointmentRepository.findByPatient(patient);
	}

}
