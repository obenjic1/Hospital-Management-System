package com.ppp.billing.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="job_paper")
public class JobPaper {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String grammage;
	
	@Column(length=255)
	private String openLength;
	
	@Column(length=255)
	private String closeLength;
	
	@Column(length=255)
	private String openWidth;
	
	@Column(length=255)
	private String closeWidth;
	
	@Column(length=10)
	private int volume;
	
	@ManyToOne
	@JoinColumn(name = "content_type_id", referencedColumnName = "id")
	private ContentType  contentType;
	
	@ManyToOne
	@JoinColumn(name = "printing_machine_id", referencedColumnName = "id")
	private PrintingMachine  printingMachine;
	
	@ManyToOne
	@JoinColumn(name = "paper_type_id", referencedColumnName = "id")
	private PaperType  paperType;
	
	@ManyToOne
	@JoinColumn(name = "job_id", referencedColumnName = "id")
	private Job  job;
	
	@OneToMany(fetch = FetchType.LAZY,  mappedBy = "jobPaper")
	private List<JobColorCombination> jobColorCombinations;




}
