package com.ppp.billing.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.Dto.ConsultationDto;
import com.ppp.billing.Dto.DoctorRevenueDTO;
import com.ppp.billing.Dto.DoctorRevenueSummary;
import com.ppp.billing.model.Appointment;
import com.ppp.billing.model.AppointmentStatus;
import com.ppp.billing.model.Consultation;
import com.ppp.billing.model.Patient;
import com.ppp.billing.repository.AppointmentRepository;
import com.ppp.billing.repository.ConsultationRepository;
import com.ppp.billing.repository.PatientRepository;
import com.ppp.billing.repository.StaffRepository;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private StaffRepository staffRepository;
    
    @Autowired
    private PatientRepository patientRepository;


    public Consultation createConsultation(Long appointmentId, Consultation consultation) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // Mark appointment as completed
        appointment.setStatus(AppointmentStatus.COMPLETED);
        appointmentRepository.save(appointment);

        consultation.setAppointment(appointment);
        return consultationRepository.save(consultation);
    }

    public List<Consultation> getConsultationsByDoctor(Long doctorId) {
        return consultationRepository.findByAppointmentDoctorId(doctorId);
    }

    public Optional<Consultation> getConsultationByAppointment(Long appointmentId) {
      
    	Optional<Consultation> consult = consultationRepository.findByAppointmentId(appointmentId);
               
    	return consult;
    }

    public List<Consultation> getAllConsultations() {
    	List<Consultation>  consultation = consultationRepository.findAll();
    	consultation.sort(Comparator.comparing(Consultation::getId));
    	Collections.reverse(consultation);
        return consultation;
    }

	public List<Consultation> consultationService(List<Appointment> appointments) {
		return consultationRepository.findByAppointmentIn(appointments) ;
	}

	public List<Consultation> findByDateRange(LocalDateTime atStartOfDay, LocalDateTime atStartOfDay2) {
		return consultationRepository.findByConsultationDateBetween(atStartOfDay, atStartOfDay2);
	}
	
	 public List<Consultation> getConsultationsByMonth(int month, int year) {
	        return consultationRepository.findAllByMonthAndYear(month, year);
	    }

	    public Map<String, Long> getConsultationCountByDoctor(int month, int year) {
	        List<Object[]> results = consultationRepository.countConsultationsByDoctor(month, year);
	        Map<String, Long> doctorCounts = new HashMap<>();
	        for (Object[] row : results) {
	            String doctor = (String) row[0];
	            Long count = (Long) row[1];
	            doctorCounts.put(doctor, count);
	        }
	        return doctorCounts;
	    }

		public Consultation createConsultation(ConsultationDto consultDto) {
			
			Consultation consultation = new Consultation();
			consultation.setReferenceNumber(generateReferenceNumber());
			consultation.setAmountPaid(consultDto.getAmountPaid());
			consultation.setConsultationDate(LocalDateTime.now());
			consultation.setDoctor(staffRepository.findById(consultDto.getDoctorId()).get());
			consultation.setPaymentType(consultDto.getPaymentType());
			consultation.setType(consultDto.getType());
			if ((Long) consultDto.getPatientId() != null) {
	    	    Patient patient = patientRepository.findById(consultDto.getPatientId()).orElse(null);
	    	    consultation.setPatient(patient);
	    	}  
			if (consultDto.getUnregisteredPatientName() != null && !consultDto.getUnregisteredPatientName().isEmpty()) {
			    consultation.setUnreegisteredPatientName(consultDto.getUnregisteredPatientName());
			    consultation.setPhoneNumber(consultDto.getPhoneNumber());
			}
			
			
			return consultationRepository.save(consultation);
		}
		
		 public DoctorRevenueSummary getMonthlyDoctorRevenueWithProfit(int month, int year) {
		        List<DoctorRevenueDTO> stats = consultationRepository.getDoctorRevenueStatsByMonth(month, year);

		        BigDecimal totalRevenue = BigDecimal.ZERO;
		        BigDecimal totalDoctorPayout = BigDecimal.ZERO;

		        for (DoctorRevenueDTO dto : stats) {
		            dto.calculateAmountToPay();
		            totalRevenue = totalRevenue.add(dto.getTotalRevenue() != null ? dto.getTotalRevenue() : BigDecimal.ZERO);
		            totalDoctorPayout = totalDoctorPayout.add(dto.getAmountToPay() != null ? dto.getAmountToPay() : BigDecimal.ZERO);
		        }

		        BigDecimal profit = totalRevenue.subtract(totalDoctorPayout);

		        return new DoctorRevenueSummary(stats, totalRevenue, totalDoctorPayout, profit);
		    }
		 private String generateReferenceNumber() {
			    String prefix = "CONS";

			    // Get current month as MM format
			    String monthPart = String.format("%02d", LocalDate.now().getMonthValue());

			    // Calculate start and end of current month
			    LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
			    LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

			    LocalDateTime startOfMonth = firstDayOfMonth.atStartOfDay();
			    LocalDateTime endOfMonth = lastDayOfMonth.atTime(LocalTime.MAX);

			    // Count how many consultations in current month
			    int countThisMonth = consultationRepository.countByConsultationDateBetween(startOfMonth, endOfMonth);

			    // Next sequence number
			    int sequence = countThisMonth + 1;

			    // Format sequence with leading zeros, 3 digits
			    String sequencePart = String.format("%03d", sequence);

			    // Build full reference number
			    return String.format("%s-%s-%s", prefix, monthPart, sequencePart);
			}

		public Optional<Consultation> findById(Long id) {
			// TODO Auto-generated method stub
			return consultationRepository.findById(id);
		}

}