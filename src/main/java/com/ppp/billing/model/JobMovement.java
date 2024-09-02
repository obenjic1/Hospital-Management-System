package com.ppp.billing.model;

import java.util.Date;
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
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "job_movement")
public class JobMovement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name="creation_date")
	private Date creationDate;
//	
//	@Column(name="source_department")
//	private Department sourceDepartment;
//	@OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.PERSIST)
//	@JoinColumn(name = "department_id", referencedColumnName = "id")
	//@Column(name="department")
//	private Department department;

//
	@ManyToOne
	@JoinColumn(name="job_id", nullable=false)
	private Job job;
	
//	@OneToOne(mappedBy = "jobMovement")
//	@JoinColumn(name="job_id", nullable=false)
//	private Job job;
	
//
	@ManyToOne
	@JoinColumn(name="department_id", nullable=false)
	private Department department;
}
