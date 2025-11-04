package com.ppp.billing.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ppp.billing.Dto.DailySaleDTO;
import com.ppp.billing.Dto.FactureItemDTO;
import com.ppp.billing.model.Facture;
import com.ppp.billing.repository.FactureRepository;
import com.ppp.billing.service.FactureService;

@Service

public class FactureServiceImpl implements FactureService {

    private final FactureRepository factureRepository;

    public FactureServiceImpl(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    @Override
    public Facture saveFacture(Facture facture) {
        return factureRepository.save(facture);
    }

    @Override
    public Facture getFactureById(Long id) {
        return factureRepository.findById(id).orElse(null);
    }

    @Override
    public Facture getFactureByVisit(Long visitId) {
        return factureRepository.findByVisitId(visitId);
    }

    @Override
    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    @Override
    public void deleteFacture(Long id) {
        factureRepository.deleteById(id);
    }

	@Override
	public List<Facture> findByVisit_Patient_IdOrderByIdDesc(Long id) {
		
		return factureRepository.findByVisit_Patient_IdOrderByIdDesc(id);
	}

	@Override
	public List<Facture> findByVisit_Patient_NameContainingIgnoreCaseOrderByIdDesc(String patientName) {
		
		return factureRepository.findByVisit_Patient_NameContainingIgnoreCaseOrderByIdDesc( patientName);
	}

	@Override
	public List<Facture> findAllByOrderByIdDesc() {
		
		return factureRepository.findAllByOrderByIdDesc();
	}

	@Override
	public List<Facture> findByCreatedAtBetweenOrderByIdDesc(LocalDate atStartOfDay, LocalDate atStartOfDay2) {
		return factureRepository.findByCreatedDateBetweenOrderByIdDesc( atStartOfDay,  atStartOfDay2);
	}
	@Override
	public List<FactureItemDTO> getFactureItemsForToday(LocalDate date) {
	    List<Object[]> raw = factureRepository.findSalesReportByDate(date);

	    return raw.stream()
	            .map(r -> new FactureItemDTO(
	                    safeString(r[0]),                            // factureRef
	                    safeString(r[1]),                            // customer
	                    safeString(r[2]),                            // reason
	                    safeString(r[3]),                            // itemName or subtype name
	                    toBigDecimal(r[4]),                          // price
	                    r[5] != null ? ((Number) r[5]).intValue() : 1, // quantity
	                    toBigDecimal(r[6]),                          // total
	                    toBigDecimal(r[7]),                          // amountPaid
	                    r[8] != null ? ((Number) r[8]).doubleValue() : 0.0, // discount
	                    safeString(r[9])                             // status
	            ))
	            .collect(Collectors.toList());
	}

	private BigDecimal toBigDecimal(Object val) {
	    if (val == null) return BigDecimal.ZERO;
	    if (val instanceof BigDecimal) return (BigDecimal) val;
	    if (val instanceof Number) return BigDecimal.valueOf(((Number) val).doubleValue());
	    return new BigDecimal(val.toString());
	}

	private String safeString(Object val) {
	    return val != null ? val.toString() : "";
	}
	public List<DailySaleDTO> getTodaysSales() {
	    LocalDate today = LocalDate.now();
	    List<Object[]> raw = factureRepository.findFacturesWithSubtypesOrSalesByDate(today);

	    return raw.stream()
	              .map(r -> new DailySaleDTO(
	                      (String) r[0],  // referenceNumber
	                      (String) r[1],  // patient/customer
	                      (String) r[2],  // subtype or 'Pharmacy Sales'
	                      (BigDecimal) Optional.ofNullable(r[3]).orElse(BigDecimal.ZERO),  // total
	                      (BigDecimal) Optional.ofNullable(r[4]).orElse(BigDecimal.ZERO),  // paid
	                      ((Number) Optional.ofNullable(r[5]).orElse(0)).doubleValue(),    // discount
	                      (String) r[6]   // status
	              ))
	              .collect(Collectors.toList());
	}



    public BigDecimal getTodaysTotalRevenue() {
        List<DailySaleDTO> sales = getTodaysSales();
        return sales.stream()
                    .map(DailySaleDTO::getNetAmount)
                    .filter(Objects::nonNull)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    public Map<String, BigDecimal> getTodayStats() {
        BigDecimal totalPaid = factureRepository.getTotalPaidToday();
        BigDecimal totalExpected = factureRepository.getTotalExpectedToday();
        BigDecimal totalPending = factureRepository.getTotalPendingToday();

        Map<String, BigDecimal> map = new HashMap<>();
        map.put("paid", totalPaid);
        map.put("expected", totalExpected);
        map.put("pending", totalPending);
        return map;
    }

    public Map<String, BigDecimal> getMonthlyStats() {
        BigDecimal totalPaid = factureRepository.getTotalPaidThisMonth();
        BigDecimal totalExpected = factureRepository.getTotalExpectedThisMonth();
        BigDecimal totalPending = factureRepository.getTotalPendingThisMonth();

        Map<String, BigDecimal> map = new HashMap<>();
        map.put("paid", totalPaid);
        map.put("expected", totalExpected);
        map.put("pending", totalPending);
        return map;
    }

 // 🔹 Count of all pending bills
    public Long getTotalPendingBills() {
        return factureRepository.getTotalPendingBills();
    }

    // 🔹 Count of pending bills created this month
    public Long getTotalPendingBillsThisMonth() {
        return factureRepository.getTotalPendingBillsThisMonth();
    }
    public BigDecimal getTotalRevenueToday() {
        BigDecimal result = factureRepository.getTotalRevenueToday();
        return result != null ? result : BigDecimal.ZERO;
    }

    public BigDecimal getTotalRevenueThisMonth() {
        BigDecimal result = factureRepository.getTotalRevenueThisMonth();
        return result != null ? result : BigDecimal.ZERO;
    }

    public Long getPaidBillsToday() {
        return factureRepository.getPaidBillsToday();
    }

    public Long getPaidBillsThisMonth() {
        return factureRepository.getPaidBillsThisMonth();
    }
	
}
