package com.ppp.billing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="job_color_combination")
public class JobColorCombination {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="front_color_number")
	private int frontColorNumber; 
	
	@Column(name="back_color_number")
	private int backColorNumber; 
	
	@Column(name="number_of_signature")
	private double numberOfSignature; 
	
	@ManyToOne
	@JoinColumn(name = "print_type_id", referencedColumnName = "id")
	private PrintType  printType;
	
	@ManyToOne
	@JoinColumn(name = "printing_machine_id", referencedColumnName = "id")
	private PrintingMachine  printingMachine;


	@ManyToOne
	@JoinColumn(name = "job_paper_id", referencedColumnName = "id")
	private JobPaper  jobPaper;
	

	
}
