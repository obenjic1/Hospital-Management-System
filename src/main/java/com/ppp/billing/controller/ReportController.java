package com.ppp.billing.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppp.billing.Dto.RevenueReportDto;
import com.ppp.billing.model.Sale;
import com.ppp.billing.repository.MedicineRepository;
import com.ppp.billing.repository.StockRequestRepository;
import com.ppp.billing.service.ReportService;
import com.ppp.billing.service.SalesService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final StockRequestRepository stockRequestRepository;
	
	 @Autowired
	private  MedicineRepository medecinrepository;
	 @Autowired
	 private SalesService saleService;
	 private final ReportService reportService;

//    ReportController(StockRequestRepository stockRequestRepository) {
//        this.stockRequestRepository = stockRequestRepository;
//    }

	    @GetMapping("/dashboard")
	    public String dashboard(Model model) {
	        model.addAttribute("todayRevenue", reportService.getTodayRevenue());
	        model.addAttribute("todayItems", reportService.getTodayItemsSold());
	        model.addAttribute("weekItems", reportService.getThisWeekItemsSold());
	        model.addAttribute("monthItems", reportService.getThisMonthItemsSold());
	        model.addAttribute("lowStock", medecinrepository.findLowStockMedicines());
	        model.addAttribute("expired", medecinrepository.findExpiredMedicines());
	        return "reports/dashboard";  
	    }
	    
	    
	    @GetMapping("/revenue/{date}")
	    public String getRevenue(@PathVariable String date, Model model) {
	        LocalDate d = LocalDate.parse(date);
	        Date startDateConverted = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
	        RevenueReportDto revenue = reportService.getDailyRevenue(startDateConverted);
            model.addAttribute("revenue", revenue);

	        return "reports/dashboard";
	    }
	    
	    
	    
	    @GetMapping("/sales/{startDate}/{endDate}")
	    public String getSales(@PathVariable String startDate,
	                           @PathVariable String endDate,
	                           Model model) {
	        LocalDate sDate;
	        LocalDate eDate;

	        try {
	            sDate = LocalDate.parse(startDate);
	            eDate = LocalDate.parse(endDate);
	        } catch (DateTimeParseException e) {
	            model.addAttribute("error", "Invalid date format.");
	            return "reports/error.jsp";
	        }
	        
	        Date startDateConverted = Date.from(sDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	        Date endDateConverted = Date.from(eDate.atStartOfDay(ZoneId.systemDefault()).toInstant());


	        List<Sale> salesList = saleService.salesReport(startDateConverted, endDateConverted);
	        
	        if (salesList == null) {
	            model.addAttribute("error", "No sales data found for the given date range.");
	        } else {
	            model.addAttribute("sales", salesList);
	        }

	        return "reports/dashboard";
	    }
	    

	    @GetMapping("/monthly")
	    public String getMonthlyReport( Model model) {
	    	LocalDate today = LocalDate.now();
	        int month = today.getMonthValue();
	        int year = today.getYear();
	        Map<LocalDate, Double> dailyReport = reportService.getDailyHospitalReport(month, year);

	        // Compute total
	        double totalRevenue = dailyReport.values().stream().mapToDouble(Double::doubleValue).sum();

	        model.addAttribute("dailyReport", dailyReport);
	        model.addAttribute("totalRevenue", totalRevenue);
	        model.addAttribute("month", month);
	        model.addAttribute("year", year);

	        return "reports/monthlyReport"; // JSP
	    }

}
