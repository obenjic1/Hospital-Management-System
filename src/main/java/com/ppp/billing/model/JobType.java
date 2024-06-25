package com.ppp.billing.model;

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
@Entity(name = "job_type")
public class JobType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "category", columnDefinition = "int default 2 comment'0 for only cover job type, 1 for only content, 2 for both content type'")
	private int category;
	
	@Column(name = "jobs")
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "jobType")
	private List<Job> jobs;

}
