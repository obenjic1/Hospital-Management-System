package com.ppp.billing.serviceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.Dto.AppointmentDto;
import com.ppp.billing.Dto.ConsultationSubtypeDTO;
import com.ppp.billing.Dto.VisitFormDTO;
import com.ppp.billing.model.Appointment;
import com.ppp.billing.model.ConsultationSubtype;
import com.ppp.billing.model.ConsultationType;
import com.ppp.billing.model.Facture;
import com.ppp.billing.model.Patient;
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
    	    /* copy fields into the MANAGED instance */
    	    pat.setAge(dto.getAge());
    	    pat.setContact(dto.getContact());
    	    pat.setEmmergenceName(dto.getEmergencyName());
    	    pat.setEmmergencyContact(dto.getEmergencyContact());
    	    pat.setGender(dto.getGender());
    	    pat.setMaritalStatus(dto.getMaritalStatus());
    	    pat.setName(dto.getFirstName());
    	    pat.setOccupation(dto.getOccupation());
    	    pat.setResidence(dto.getResidence());
    	    pat.addTracking("EDIT", "edited during visit");
    	    /* DO NOT call another service – just let the TX commit */
    	} else {
    	    pat = new Patient();
    	    /* copy DTO fields */
    	    pat.setAge(dto.getAge());
    	    pat.setContact(dto.getContact());
    	    pat.setEmmergenceName(dto.getEmergencyName());
    	    pat.setEmmergencyContact(dto.getEmergencyContact());
    	    pat.setGender(dto.getGender());
    	    pat.setMaritalStatus(dto.getMaritalStatus());
    	    pat.setName(dto.getFirstName());
    	    pat.setOccupation(dto.getOccupation());
    	    pat.setResidence(dto.getResidence());
    	    pat.addTracking("CREATE", "created during visit");
    	    pat = patientService.createPatient(pat);   // save once
    	}

    	// 2. Visit – use the MANAGED patient
    	Visit visit = new Visit();
    	visit.setPatient(pat);   // now patient is in the current session
        
        if (dto.getDoctorId() != null) {
            Staff doctor = staffService.findById(dto.getDoctorId()).get();
            visit.setAttendingStaff(doctor);
        } 
         // Set staff from doctor ID
        visit.setVisitDate(dto.getVisitDate());
        visit.setVisitTime(dto.getVisitTime());
        visit.setConsultationType(consultationTypeService.findById(dto.getReasonId()));  // Set consultation type

        // 3. Services (ConsultationSubtype / Pharmacy / Exam)
       

        

        if (Boolean.TRUE.equals(dto.getCreateAppointment())) {
            AppointmentDto appt = new AppointmentDto();
            appt.setReason(dto.getAppointmentReason());
            appt.setAppoitmentDate(dto.getAppointmentDate());
            appt.setPatientId(pat.getId());
            if (dto.getDoctorId() != null && dto.getDoctorId() > 0) {
    staffService.findById(dto.getDoctorId())
                .ifPresent(visit::setAttendingStaff);
}
            
           
            appt.setStatus("PENDING");// Set doctor for appointment
           Appointment aps =  appointmentService.createAppointment(appt);  // Save appointment
            visit.setAppointment(aps);  // Link appointment to visit
        }

       

        List<ConsultationSubtype> cons = new ArrayList<>();
      
        for (ConsultationSubtypeDTO vs : dto.getServices()) {
            if (vs.getId() == null || vs.getId() <= 0) continue;
            ConsultationSubtype sub = consultationSubtypeService.findById(vs.getId());
            sub.setVisit(visit);
            cons.add(sub);
        }
   
        visit.setSubtypes(cons);
        

        Facture facture = new Facture();
        facture.setVisit(visit);                     
        facture.setTotalAmount(dto.getTotalAmount());
        facture.setDiscount(dto.getDiscount());
        facture.setNetAmount(
            dto.getTotalAmount()
               .subtract(BigDecimal.valueOf(dto.getTotalAmount().doubleValue() * dto.getDiscount() / 100))
        );
		        BigDecimal discountAmount = dto.getTotalAmount()
		                .multiply(BigDecimal.valueOf(dto.getDiscount()))
		                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
		
		facture.setDiscountAmount(discountAmount);      
		facture.setNetAmount(dto.getTotalAmount().subtract(discountAmount)); 
        facture.setStatus("PENDING");
        facture.setAmountPaid(0);
        facture.setBalance(facture.getNetAmount().doubleValue());
        facture.setCreatedDate(dto.getVisitDate());

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
//	@Override
//	public Object findByPatientIdOrderByIdDesc(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//   
}