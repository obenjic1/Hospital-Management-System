package com.ppp.billing.model;

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
	private Long id;
	private int frontColorNumber; 
	private int backColorNumber; 
	private int numberOfSignature; 
	
	@ManyToOne
	@JoinColumn(name = "print_type_id", referencedColumnName = "id")
	private PrintType  printType;

	@ManyToOne
	@JoinColumn(name = "content_type_id", referencedColumnName = "id")
	private ContentType  contentType;
	
	@ManyToOne
	@JoinColumn(name = "job_id", referencedColumnName = "id")
	private Job  job;


	
}
