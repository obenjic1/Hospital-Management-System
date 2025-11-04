package com.ppp.billing.serviceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.Dto.AppointmentDto;
import com.ppp.billing.Dto.ConsultationSubtypeDTO;
import com.ppp.billing.Dto.DoctorActivityDTO;
import com.ppp.billing.Dto.DoctorConsultationDTO;
import com.ppp.billing.Dto.DoctorConsultationStatsDTO;
import com.ppp.billing.Dto.SubtypeStatsDTO;
import com.ppp.billing.Dto.VisitFormDTO;
import com.ppp.billing.model.Appointment;
import com.ppp.billing.model.ConsultationSubtype;
import com.ppp.billing.model.Facture;
import com.ppp.billing.model.Patient;
import com.ppp.billing.model.RefrenceNumberGenerator;
import com.ppp.billing.model.Staff;
import com.ppp.billing.model.Visit;
import com.ppp.billing.repository.FactureRepository;
import com.ppp.billing.repository.VisitRepository;
import com.ppp.billing.service.AppoitmentService;
import com.ppp.billing.service.ConsultationSubtypeService;
import com.ppp.billing.service.ConsultationTypeService;
import com.ppp.billing.service.PatientService;
import com.ppp.billing.service.StaffService;
import com.ppp.billing.service.VisitService;
import com.ppp.user.repository.UserRepository;

@Service
public class VisitServiceImpl implements VisitService {

    private final FactureRepository factureRepository;

    private final VisitRepository visitRepository;
    
    @Autowired
    private final PatientService patientService;
    
    @Autowired 
    private  FactureRepository factureRepo;;
    
    @Autowired
    private ConsultationTypeService consultationTypeService;
    
    @Autowired 
    private  ConsultationSubtypeService consultationSubtypeService ;
    @Autowired 
    private  AppoitmentService appointmentService ;
    
    @Autowired
    private final StaffService staffService;
    
    @Autowired
    private UserRepository userrepository;
    
    

    public VisitServiceImpl(VisitRepository visitRepository,PatientService patientService,StaffService staffService, FactureRepository factureRepository) {
        this.visitRepository = visitRepository;
        this.patientService = patientService;
        this.staffService = staffService;
        this.factureRepository = factureRepository;
    }
    @Transactional
    public Visit saveVisit(VisitFormDTO dto) {
        Patient pat;
        if (dto.getPatientId() != null && dto.getPatientId() > 0) {
            pat = patientService.findById(dto.getPatientId());
            // update patient fields
//            pat.setAge(dto.getAge());
//            pat.setContact(dto.getContact());
//            pat.setEmmergenceName(dto.getEmergencyName());
//            pat.setEmmergencyContact(dto.getEmergencyContact());
//            pat.setGender(dto.getGender());
//            pat.setMaritalStatus(dto.getMaritalStatus());
//            pat.setName(dto.getFirstName());
//            pat.setOccupation(dto.getOccupation());
//            pat.setResidence(dto.getResidence());
        } else {
            pat = new Patient();
            pat.setAge(dto.getAge());
            pat.setContact(dto.getContact());
            pat.setEmmergenceName(dto.getEmergencyName());
            pat.setEmmergencyContact(dto.getEmergencyContact());
            pat.setGender(dto.getGender());
            pat.setMaritalStatus(dto.getMaritalStatus());
            pat.setName(dto.getFirstName());
            pat.setOccupation(dto.getOccupation());
            pat.setResidence(dto.getResidence());
            pat.setReferenceNumber(RefrenceNumberGenerator.nextPatientRef());
            pat.addTracking("CREATE", "created during visit");
            pat = patientService.createPatient(pat);
        }

        // Create Visit
        Visit visit = new Visit();
        visit.setPatient(pat);

        if (dto.getDoctorId() != null) {
            staffService.findById(dto.getDoctorId())
                .ifPresent(visit::setAttendingStaff);
        }

        visit.setVisitDate(dto.getVisitDate());
        visit.setVisitTime(dto.getVisitTime());
        visit.setConsultationType(consultationTypeService.findById(dto.getReasonId()));

        // Handle appointment if needed
        if (Boolean.TRUE.equals(dto.getCreateAppointment())) {
            AppointmentDto appt = new AppointmentDto();
            appt.setReason(dto.getAppointmentReason());
            appt.setAppoitmentDate(dto.getAppointmentDate());
            appt.setPatientId(pat.getId());
            appt.setStatus("PENDING");
            if (dto.getDoctorId() != null && dto.getDoctorId() > 0) {
                staffService.findById(dto.getDoctorId())
                    .ifPresent(visit::setAttendingStaff);
            }
            Appointment aps = appointmentService.createAppointment(appt);
            visit.setAppointment(aps);
        }

        // ✅ Collect existing subtypes (no duplication)
        List<ConsultationSubtype> cons = new ArrayList<>();
        for (ConsultationSubtypeDTO vs : dto.getServices()) {
            if (vs.getId() == null || vs.getId() <= 0) continue;
            ConsultationSubtype sub = consultationSubtypeService.findById(vs.getId());
            cons.add(sub); // just add to list
        }
        visit.setSubtypes(cons);

        // Build facture
        Facture facture = new Facture();
        facture.setVisit(visit);
        facture.setTotalAmount(dto.getTotalAmount());
        facture.setDiscount(dto.getDiscount());

        BigDecimal discountAmount = dto.getTotalAmount()
                .multiply(BigDecimal.valueOf(dto.getDiscount()))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        facture.setDiscountAmount(discountAmount);
        facture.setNetAmount(dto.getTotalAmount().subtract(discountAmount));
        facture.setStatus("PENDING");
        facture.setReferenceNumber(RefrenceNumberGenerator.nextInvoiceRef());
        facture.setBalance(facture.getNetAmount().doubleValue());
        facture.setCreatedDate(dto.getVisitDate());
        facture.setAmountPaid(BigDecimal.ZERO);

        facture = factureRepo.save(facture);
        visit.setFacture(facture);

       visitRepository.save(visit);

        return visit;
    }
    
    
    @Override
    public Visit saveVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public Visit getVisitById(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    @Override
    public List<Visit> getVisitsByPatient(Long patientId) {
        return visitRepository.findByPatientId(patientId);
    }

    @Override
    public void deleteVisit(Long id) {
        visitRepository.deleteById(id);
    }
    
    @Override
    public List<SubtypeStatsDTO> getDailySubtypeStats(LocalDate date) {
        List<Object[]> raw = visitRepository.findSubtypesByDate(date, date);
        return raw.stream()
                  .map(r -> new SubtypeStatsDTO((String) r[0], (Long) r[1], (BigDecimal) r[2]))
                  .collect(Collectors.toList());
    }

    @Override
    public List<SubtypeStatsDTO> getWeeklySubtypeStats(LocalDate date) {
        LocalDate startOfWeek = date.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek   = date.with(DayOfWeek.SUNDAY);
        List<Object[]> raw = visitRepository.findSubtypesByDate(startOfWeek, endOfWeek);
        return raw.stream()
                  .map(r -> new SubtypeStatsDTO((String) r[0], (Long) r[1], (BigDecimal) r[2]))
                  .collect(Collectors.toList());
    }

    @Override
    public List<SubtypeStatsDTO> getMonthlySubtypeStats(LocalDate date) {
        LocalDate startOfMonth = date.withDayOfMonth(1);
        LocalDate endOfMonth   = date.withDayOfMonth(date.lengthOfMonth());
        List<Object[]> raw = visitRepository.findSubtypesByDate(startOfMonth, endOfMonth);
        return raw.stream()
                  .map(r -> new SubtypeStatsDTO((String) r[0], (Long) r[1], (BigDecimal) r[2]))
                  .collect(Collectors.toList());
    }
    
    @Override
    public void debugVisits(LocalDate start, LocalDate end) {
        List<Object[]> results = visitRepository.findVisitsWithSubtypes(start, end);
        for (Object[] row : results) {
            System.out.println("Visit ID: " + row[0] +
                               ", Date: " + row[1] +
                               ", Subtype: " + row[2] +
                               ", Price: " + row[3]);
        }
    }

//    @Override
//    public List<DoctorActivityDTO> getMostActiveDoctors(LocalDate startDate, LocalDate endDate) {
//        List<Object[]> raw = visitRepository.findMostActiveDoctors(startDate, endDate);
//        return raw.stream()
//                  .map(r -> new DoctorActivityDTO(
//                          (Long) r[0],
//                          (String) r[1],
//                          (Long) r[2]
//                  ))
//                //  .collect(Collectors.toList());
//    }
    
    public List<DoctorConsultationDTO> getDoctorConsultations(LocalDate start, LocalDate end) {
        List<Object[]> raw = visitRepository.findDoctorConsultationsByDate(start, end);

        return raw.stream().map(r -> {
            Long staffId = (Long) r[0];
            String firstName = (String) r[1];
            String lastName = (String) r[2];
            String consultationName = (String) r[3];
            BigDecimal totalGenerated = (BigDecimal) r[4];

            Staff doctor = staffService.findById(staffId).get();
            BigDecimal doctorPay = totalGenerated.multiply(doctor.getPercentage()).divide(BigDecimal.valueOf(100));
            BigDecimal hospitalProfit = totalGenerated.subtract(doctorPay);

            return new DoctorConsultationDTO(doctor.getName(), consultationName, totalGenerated, doctorPay, hospitalProfit);
        }).collect(Collectors.toList());
    }
    
    public List<DoctorConsultationStatsDTO> getDoctorConsultationStats(LocalDate startDate, LocalDate endDate) {
        List<Object[]> raw = visitRepository.findDoctorConsultationsByDate(startDate, endDate);

        List<DoctorConsultationStatsDTO> stats = new ArrayList<>();

        for (Object[] r : raw) {
            Long doctorId = r[0] != null ? ((Number) r[0]).longValue() : 0L;
            String firstName = r[1] != null ? r[1].toString() : "";
            String lastName = r[2] != null ? r[2].toString() : "";
            String consultationName = r[3] != null ? r[3].toString() : "";
            Long count = r[4] != null ? ((Number) r[4]).longValue() : 0L;
            BigDecimal totalAmount = r[5] != null ? new BigDecimal(r[5].toString()) : BigDecimal.ZERO;
            BigDecimal percentage = r[6] != null ? new BigDecimal(r[6].toString()) : BigDecimal.ZERO; // safe default

            BigDecimal doctorPay = totalAmount.multiply(percentage).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            BigDecimal hospitalProfit = totalAmount.subtract(doctorPay);

            stats.add(new DoctorConsultationStatsDTO(
                    doctorId,
                    firstName + " " + lastName,
                    consultationName,
                    count,
                    totalAmount,
                    doctorPay,
                    hospitalProfit
            ));
        }

        return stats;
    }

	@Override
	public List<DoctorActivityDTO> getMostActiveDoctors(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<SubtypeStatsDTO> getStatsForDateRange(LocalDate startDate, LocalDate endDate) {
		 List<Object[]> raw = visitRepository.findSubtypesByDate(startDate, endDate);
	        return raw.stream()
	                  .map(r -> new SubtypeStatsDTO((String) r[0], (Long) r[1], (BigDecimal) r[2]))
	                  .collect(Collectors.toList());
	}


}