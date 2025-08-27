package com.ppp.billing.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.context.SecurityContextHolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor 
@AllArgsConstructor 
@ToString
public class Patient {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private String gender;
    private String contact;
    
    
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tracking> tracking = new ArrayList<>();

    // helper method to add history
    public void addTracking(String action, String description) {
  		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
  	   
        Tracking t = new Tracking();
        t.setAction(action);
        t.setDescription(description);
        t.setPerformedBy(userName);
        t.setCreationDate(LocalDateTime.now());
        t.setPatient(this);
        this.tracking.add(t);
    }
}
