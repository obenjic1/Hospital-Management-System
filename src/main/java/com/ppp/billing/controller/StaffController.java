package com.ppp.billing.controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ppp.billing.model.Staff;
import com.ppp.billing.service.StaffService;

@Controller
	@RequestMapping("/staff")
	public class StaffController {

	    @Autowired
	    private StaffService staffService;

	    @GetMapping
	    public String listStaff(Model model) {
	        model.addAttribute("staffList", staffService.findAll());
	        return "staff/list"; 
	    }

	    @GetMapping("/add")
	    public String showAddForm(Model model) {
	        model.addAttribute("staff", new Staff());
	        return "staff/form";  
	    }

	    @PostMapping("/save")
	    public String saveStaff(@ModelAttribute Staff staff) {
	        staffService.save(staff);
	        return "redirect:/staff";
	    }

	    @GetMapping("/edit/{id}")
	    public String showEditForm(@PathVariable Long id, Model model) {
	        staffService.findById(id).ifPresent(staff -> model.addAttribute("staff", staff));
	        return "staff/form";
	    }

	    @GetMapping("/delete/{id}")
	    public String deleteStaff(@PathVariable Long id) {
	        staffService.deleteById(id);
	        return "redirect:/staff";
	    }
	

}
