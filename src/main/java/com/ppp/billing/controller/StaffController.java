package com.ppp.billing.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ppp.billing.model.Medicine;
import com.ppp.billing.model.Staff;
import com.ppp.billing.service.DepartmentService;
import com.ppp.billing.service.StaffService;

@Controller
	@RequestMapping("/staff")
	public class StaffController {


	    @Autowired
	    private StaffService staffService;
	    

	    @Autowired
	    private DepartmentService departmentService;

	    @GetMapping
	    public String listStaff(Model model) {
	        model.addAttribute("staffList", staffService.getAllStaff());
	        return "staff/staff-list";
	    }

	    @GetMapping("/new")
	    public String showAddForm(Model model) {
	    	
	        model.addAttribute("staff", new Staff());
	        model.addAttribute("departments", departmentService.findAll());
	        return "staff/add-staff";
	    }
	    
	    @PostMapping
	    public ResponseEntity<String> saveStaff(@ModelAttribute Staff staff) {
	        try { 
	        	 staffService.saveStaff(staff);
	    	return new ResponseEntity<>(HttpStatus.CREATED);
			} catch (Exception e) {
				
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);	}
	    }

	    @GetMapping("/edit/{id}")
	    public String showEditForm(@PathVariable Long id, Model model) {
	        Staff staff = staffService.getStaffById(id);
	        model.addAttribute("staff", staff);
	        model.addAttribute("departments",departmentService.findAll());
	        return "staff/staff-form";
	    }

	    @GetMapping("/delete/{id}")
	    public String deleteStaff(@PathVariable Long id) {
	        staffService.deleteStaff(id);
	        return "redirect:/staff";
	    }
	
	    // Save new medicine
        @PostMapping("/edit/{id}")
        public ResponseEntity<String> editStaff(@PathVariable("id") Long id ,  @ModelAttribute Staff staff, RedirectAttributes redirectAttributes) {
            staffService.updateStaff(id, staff);
            redirectAttributes.addFlashAttribute("success", "Staff Edited successfully!");
            return   new ResponseEntity<>(HttpStatus.CREATED);

        }

        @PostMapping("/enable/{id}")
        public ResponseEntity<String> enable(@PathVariable long id) {
       	try {
       		staffService.enableUser(id);
       		return new ResponseEntity<String>("Success", HttpStatus.OK);
       			} catch (Exception e) {
       				return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
       			}
       	}

        
        
        
        

}
