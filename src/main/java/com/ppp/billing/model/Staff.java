package com.ppp.billing.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "staff")
@Data                  
@NoArgsConstructor     
@AllArgsConstructor 
public class Staff {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;

	    private String email;

	    private String phone;

	    @ManyToOne
	    @JoinColumn(name = "department_id")
	    private Department department;

	    private String role; // e.g., DOCTOR, PHARMACIST, ACCOUNTANT, ADMIN

	    private BigDecimal salary;

}
