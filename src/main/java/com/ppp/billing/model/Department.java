package com.ppp.billing.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "department")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name",length = 255)
	private String name;
	
	@OneToMany(mappedBy="department")
	private List<JobMovement> jobMovements;
	
//	private JobMovement jobMovement;

//	@OneToOne(mappedBy = "department")
//	private JobMovement jobMovement;
}
