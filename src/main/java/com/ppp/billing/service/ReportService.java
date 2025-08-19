package com.ppp.billing.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ppp.billing.model.Medicine;
import com.ppp.billing.repository.MedicineRepository;
import com.ppp.billing.repository.SaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {

	
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
	
}
