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
@Entity(name="job_activity_option")
public class JobActivityOption {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 255)
	private String name;

	@ManyToOne
	@JoinColumn(name = "job_operation_option_id", referencedColumnName = "id")
	private JobOperationOption  jobOperationOption;
   
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "jobActivityOption")
	private List<JobActivity> jobActivities;
	

}

