package com.ppp.billing.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.protobuf.TextFormat.ParseException;
import com.ppp.billing.repository.SaleRepository;
import com.ppp.billing.service.SaleService;

@Controller
@RequestMapping("/stats")
public class StatsController {
	
	@Autowired private SaleService saleService;
	@Autowired private SaleRepository saleRepo;

    /* ------ LIVE DASHBOARD ------------------------------------------ */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "stats/dashboard";   // WEB-INF/jsp/stats/dashboard.jsp
    }

    /* ------ SINGLE DAY REPORT --------------------------------------- */
    @GetMapping("/daily")
    public String daily(@RequestParam(value = "date", required = false) String dateStr,
            Model model) throws ParseException, java.text.ParseException {
		
		if (dateStr == null || dateStr.isEmpty()) {
		dateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date date = sdf.parse(dateStr);


        model.addAttribute("date", dateStr);
        model.addAttribute("items", 
        		saleRepo.getTotalItemsSoldByDate(
        	        new java.sql.Date(date.getTime())
        	    ));
        return "stats/monthly";
    }

   

}
