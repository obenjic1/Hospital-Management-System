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
@Entity(name="job_paper")
public class JobPaper {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 50)
	private int grammage;
	@Column(length=255)
	private String openLength;
	private String closeLength;
	private String openWidth;
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
	
	




}
