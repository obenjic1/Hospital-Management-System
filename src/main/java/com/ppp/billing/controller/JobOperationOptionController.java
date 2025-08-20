package com.ppp.billing.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/job-opr-opt")
public class JobOperationOptionController {

	@Value("${paginationSise}")
	private int jobOperOpaginationSize;
	
	
	
//<------------------- Pagination -------------------->
	@GetMapping("/list")
	public String list(Model model) {
		return pagination(1, model);
	}
	
//<-------------------- Pagination ---------------------->
	@GetMapping("/pagination/{pageNo}")
	public String pagination(@RequestParam int pageNo, Model model) {
		int pageSize = jobOperOpaginationSize;
	

		return "setting/list-job-opr-opt";
	}
	
}
