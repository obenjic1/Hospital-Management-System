package com.ppp.billing.model;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="printing_machine")
public class PrintingMachine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true, length = 255)
	private String name;
	@Column(length = 50)
	private int plateLength; 
	@Column(length = 50)
	private int plateWidth; 
	private boolean is_active = Boolean.TRUE;
	private String thumbnail;
	private Date creation_date;
	
	@OneToMany(fetch = FetchType.EAGER,  mappedBy = "printingMachine")
	private List<JobPaper> jobPaper;
	
}
 