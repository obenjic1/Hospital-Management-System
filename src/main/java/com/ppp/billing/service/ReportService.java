package com.ppp.billing.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.Dto.RevenueReportDto;
import com.ppp.billing.model.Medicine;
import com.ppp.billing.repository.MedicineRepository;
import com.ppp.billing.repository.PaymentItemRepository;
import com.ppp.billing.repository.SaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {

	 @Autowired
	  private PaymentItemRepository paymentRepo;
	 
	

	    @Autowired
	    private SaleRepository pharmacyRepo;
	private final SaleRepository saleRepository;
    private final MedicineRepository medicineRepository;

    public BigDecimal getTodayRevenue() {
        return saleRepository.getTotalRevenueToday();
    }

    public Long getTodayItemsSold() {
        return saleRepository.getTotalItemsSoldToday();
    }

    public Long getThisWeekItemsSold() {
        return saleRepository.getTotalItemsSoldThisWeek();
    }

    public Long getThisMonthItemsSold() {
        return saleRepository.getTotalItemsSoldThisMonth();
    }

    public BigDecimal getSalesByUser(Long pharmacistId) {
        return saleRepository.getTotalSalesByUser(pharmacistId);
    }
	
    public List<Medicine> getExpiredMedicines() {
        return medicineRepository.findExpiredMedicines();
    }

    public List<Medicine> getLowStockMedicines() {
        return medicineRepository.findLowStockMedicines();
    }

    public List<Medicine> getOutOfStockMedicines() {
        return medicineRepository.findOutOfStockMedicines();
    }

    public List<Medicine> getMedicinesExpiringSoon() {
        return medicineRepository.findMedicinesExpiringSoon();
    }
    
    

    public RevenueReportDto getDailyRevenue(Date date) {
        BigDecimal serviceRevenue = paymentRepo.sumPaymentsByDate(date);
        BigDecimal pharmacyRevenue = pharmacyRepo.sumSalesByDate(date);
        return new RevenueReportDto(serviceRevenue, pharmacyRevenue);
    }
    
    public Map<LocalDate, Double> getDailyHospitalReport(int month, int year) {
        Map<LocalDate, Double> report = new TreeMap<>(); // keeps days sorted

     // Pharmacy sales
        List<Object[]> pharmacySales = saleRepository.getDailyPharmacySales(month, year);
        for (Object[] row : pharmacySales) {
            // Assuming row[0] is LocalDateTime instead of java.sql.Date
            LocalDate date = ((LocalDateTime) row[0]).toLocalDate(); // Use LocalDateTime
            Double total = ((Number) row[1]).doubleValue();
            report.put(date, report.getOrDefault(date, 0.0) + total);
        }

        // Service payments
        List<Object[]> servicePayments = paymentRepo.getDailyServicePayments(month, year);
        for (Object[] row : servicePayments) {
            // Assuming row[0] is LocalDateTime instead of java.sql.Date
            LocalDate date = ((LocalDateTime) row[0]).toLocalDate(); // Use LocalDateTime
            Double total = ((Number) row[1]).doubleValue();
            report.put(date, report.getOrDefault(date, 0.0) + total);
        }

        return report;
    }
	
}
