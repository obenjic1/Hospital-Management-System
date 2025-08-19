package com.ppp.billing.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.ActivityLog;
import com.ppp.billing.model.Payment;
import com.ppp.billing.model.Staff;


@Service
public class AccountingService {
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	ActivityLogService activityLogService;
	
	
	public Payment payStaff(Staff staff, String actor) {
      
        Payment savedPayment = paymentService.payStaff(staff.getId(),staff.getSalary(),LocalDate.now());

        ActivityLog log = new ActivityLog();
        log.setAction("Paid " + staff.getName() + " amount=" + staff.getSalary());
        log.setActor(actor);
        log.setTimestamp(LocalDateTime.now());
        activityLogService.save(log);

        return savedPayment;
    }


    // Total payments for a specific month/year
    public BigDecimal getTotalPaymentsForMonth(int year, int month) {
        return paymentService.getTotalPaymentsForMonth(year, month);
    }
}
