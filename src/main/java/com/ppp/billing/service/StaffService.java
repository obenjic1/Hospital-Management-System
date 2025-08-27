package com.ppp.billing.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.Patient;
import com.ppp.billing.model.Staff;
import com.ppp.billing.repository.StaffRepository;
import com.ppp.user.model.User;

@Service
public class StaffService {
	

	    @Autowired
	    private StaffRepository staffRepository;

	    public List<Staff> findAll() {
	    	
	    	List<Staff> staffs = staffRepository.findAll();
	    	staffs.sort(Comparator.comparing(Staff::getFirstName));

	        return staffs ;
	    }

	    public Optional<Staff> findById(Long id) {
	        return staffRepository.findById(id);
	    }

	

	    public void deleteById(Long id) {
	        staffRepository.deleteById(id);
	    }
	
	    

	       

	        public List<Staff> getAllStaff() {
	            return staffRepository.findAll();
	        }

	        public Staff getStaffById(Long id) {
	            return staffRepository.findById(id)
	                    .orElseThrow(() -> new RuntimeException("Staff not found"));
	        }

	        public Staff saveStaff(Staff staff) {
	            return staffRepository.save(staff);
	        }

	        public Staff updateStaff(Long id, Staff updated) {
	            Staff staff = getStaffById(id);
	            staff.setFirstName(updated.getFirstName());
	            staff.setLastName(updated.getLastName());
	            staff.setDepartment(updated.getDepartment());
	            staff.setSpeciality(updated.getSpeciality());
	            staff.setPhone(updated.getPhone());
	            staff.setEmail(updated.getEmail());
	            staff.setAddress(updated.getAddress());
	            staff.setActive(updated.isActive());
	            staff.setGender(updated.getGender());
	            return staffRepository.save(staff);
	        }

	        public void deleteStaff(Long id) {
	            staffRepository.deleteById(id);
	        }
	        
	        
	    	public void  enableUser(long id) {	
	    		Staff staff = staffRepository.findById(id).get();
	    		if(staff.isActive()) {
	    			staff.setActive(false);
	    			staffRepository.save(staff);
	    		}
	    		else {
	    			staff.setActive(true);	
	    			staffRepository.save(staff);
	    			}
	    		}
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	    }

	    
	    
	    
	    



