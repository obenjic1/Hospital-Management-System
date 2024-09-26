package com.ppp.billing.model;

import java.util.List;

import javax.persistence.CascadeType;
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
	
	@Column(name = "grammage", nullable = false)
	private int grammage;
	
	@Column(name = "volume")
	private int volume;
	
	@Column(name = "unit_price", columnDefinition = "int default 0")
	private int unitPrice;
	
	@ManyToOne
	@JoinColumn(name = "content_type_id", referencedColumnName = "id")
	private ContentType  contentType;
	
	@ManyToOne
	@JoinColumn(name = "paper_type_id", referencedColumnName = "id")
	private PaperType  paperType;
	
	@ManyToOne
	@JoinColumn(name = "job_id", referencedColumnName = "id")
	private Job  job;
	
	@OneToMany(mappedBy = "jobPaper", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<JobColorCombination> jobColorCombinations;




}
