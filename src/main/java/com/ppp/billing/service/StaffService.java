package com.ppp.billing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppp.billing.model.Staff;
import com.ppp.billing.repository.StaffRepository;

@Service
public class StaffService {
	

	    @Autowired
	    private StaffRepository staffRepository;

	    public List<Staff> findAll() {
	        return staffRepository.findAll();
	    }

	    public Optional<Staff> findById(Long id) {
	        return staffRepository.findById(id);
	    }

	    public Staff save(Staff staff) {
	        return staffRepository.save(staff);
	    }

	    public void deleteById(Long id) {
	        staffRepository.deleteById(id);
	    }
	


}
