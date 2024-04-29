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

import com.ppp.billing.model.ContentType;
import com.ppp.billing.serviceImpl.ContentTypeServiceImpl;

@Controller
@RequestMapping("/contenttype")
public class ContentTypeController {

	@Value("${paginationSise}")
	private int contentYpepagination;
	
	@Autowired
	private ContentTypeServiceImpl contentTypeServiceImpl;
	
//<--------------------- List all wit pagination ------------------->
	@GetMapping("/list")
	public String list(Model model) {
		return pagination(1, model);
	}
	
//<--------------------- Pagination ------------------->
	@GetMapping("pagination/{pageNo}")
	public String pagination(@PathVariable int pageNo, Model model) {
		int pageSize = contentYpepagination;
		Page<ContentType> contentType = contentTypeServiceImpl.findAllpaginate(pageNo, pageSize);
		List<ContentType> result = contentType.getContent();
		 model.addAttribute("currentPage", pageNo);
		 model.addAttribute("totalPages", contentType.getTotalPages());
		 model.addAttribute("totalItems", contentType.getTotalElements());
		 model.addAttribute("allContent", result);
		 
		return "setting/list-content-type";
	}
}

