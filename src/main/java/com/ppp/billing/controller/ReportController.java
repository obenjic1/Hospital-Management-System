package com.ppp.billing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppp.billing.service.ReportService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {
	
	
	 private final ReportService reportService;

	    @GetMapping("/dashboard")
	    public String dashboard(Model model) {
	        model.addAttribute("todayRevenue", reportService.getTodayRevenue());
	        model.addAttribute("todayItems", reportService.getTodayItemsSold());
	        model.addAttribute("weekItems", reportService.getThisWeekItemsSold());
	        model.addAttribute("monthItems", reportService.getThisMonthItemsSold());
	        model.addAttribute("lowStock", reportService.getLowStockMedicines());
	        model.addAttribute("expired", reportService.getExpiredMedicines());
	        System.out.println("Expired medicines " + reportService.getExpiredMedicines().size());
	        System.out.println("low stocks " + reportService.getLowStockMedicines().size());
	        return "reports/dashboard";  
	    }

}
