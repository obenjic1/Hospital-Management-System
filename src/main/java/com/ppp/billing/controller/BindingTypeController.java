package com.ppp.billing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppp.billing.model.BindingType;
import com.ppp.billing.service.BindingTypeService;

@Controller
@RequestMapping("/bindingtype")
public class BindingTypeController {
	
	@Value("${paginationSise}")
	private int bindingtypePaginationSise;
	
	@Autowired
	private BindingTypeService bindingTypeService;

//<------------------- List with pagination -------------------->		
	@GetMapping("/list")
	public String list(Model model) {
		List<BindingType> bindingTypes = bindingTypeService.listAll();
		int totalElement = bindingTypes.size();
		model.addAttribute("bindingTypes", bindingTypes);
		model.addAttribute("totalElement", totalElement);
		return "setting/list-bindingtype";
	}

//<------------------- Pagination -------------------->	
	@GetMapping("/pagination/{pageNo}")
	public String pagination(@PathVariable int pageNo, Model model) {
		int pageSize = bindingtypePaginationSise;
		Page<BindingType> bindingType = bindingTypeService.findPaginatedJobStatus(pageNo, pageSize);
		List<BindingType> result = bindingType.getContent();
		 model.addAttribute("currentPage", pageNo);
		 model.addAttribute("totalPages", bindingType.getTotalPages());
		 model.addAttribute("totalItems", bindingType.getTotalElements());
		 model.addAttribute("allBinding", result);
		 
		return "setting/list-bindingtype";
	}
	
//<------------------- Pagination -------------------->	
	@GetMapping("find/{id}")
	public String findById(@PathVariable int id, Model model) {
		try {
			 model.addAttribute("bindingType",  bindingTypeService.findById(id));
			 return "setting/binding-type-details";
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	
	@GetMapping("display/{id}")
	public String displayForm(@PathVariable int id,  Model model) {
		try {
			model.addAttribute("bindingUpdate", bindingTypeService.findById(id));
			return "setting/binding-type-update";
		} catch (Exception e) {
			throw e;
		}
	}
	
	@PostMapping("update/{id}")
	public BindingType update(@PathVariable int id, BindingType bindingType) {
		try {
			return  bindingTypeService.update(bindingType, id);
		} catch (Exception e) {
			throw e;
		}
	}

}
