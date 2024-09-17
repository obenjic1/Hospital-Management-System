package com.ppp.billing.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ppp.user.model.User;

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

	@ManyToOne
	@JoinColumn(name="job_id", nullable=false)
	private Job job;
	
//	@OneToOne(mappedBy = "jobMovement")
//	@JoinColumn(name="job_id", nullable=false)
//	private Job job;
	
//
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="department_id", nullable=false)
	private Department department;
}
