package com.ppp.billing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppp.billing.model.PrintType;
import com.ppp.billing.serviceImpl.PrintTypeServiceImpl;

@Controller
@RequestMapping("/print-type")
public class PrintTypeController {
	
	@Value("${paginationSise}")
	private int printTypeSize;
	
	@Autowired
	private PrintTypeServiceImpl printTypeServiceImpl;
	
//<---------------------------- List with pagination --------------------------->
	@GetMapping("/list")
	public String list(Model model) {
		return pagination(1, model);
	}
	
//<---------------------------- List with pagination --------------------------->
	@GetMapping("pagination/{pageNo}")
	public String pagination(@PathVariable int pageNo, Model model) {
		int pageSize = printTypeSize;
		Page<PrintType> printType = printTypeServiceImpl.paginatedList(pageNo, pageSize);
		List<PrintType> result = printType.getContent();
		 model.addAttribute("currentPage", pageNo);
		 model.addAttribute("totalPages", printType.getTotalPages());
		 model.addAttribute("totalItems", printType.getTotalElements());
		 model.addAttribute("allPrintType", result);
		 
		return "setting/list-print-type";
	}

}
