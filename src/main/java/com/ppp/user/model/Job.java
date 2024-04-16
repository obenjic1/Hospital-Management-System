package com.ppp.user.model;

import java.util.Date;
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
@Entity(name = "job")
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length =  255)
	private String title;
	@Column(nullable = false, length = 15)
	private String referenceNumber;
	@Column(length = 255)
	private String description;
	@Column(length = 15)
	private int contentVolume;
	@Column(length = 15)
	private int coverVolume;
	@Column(length = 15)
	private int contentSignature;
	@Column(length = 15)
	private int coverSignature;
	private boolean existingPlate = Boolean.FALSE;
	private Date readytoPrintDate;
	private Date expectedDeliveryDate;
	private Date creationDate;
	
	@ManyToOne
	@JoinColumn(name = "bindingType_id", referencedColumnName = "id")
	private BindingType bindingType;
	
	@ManyToOne
	@JoinColumn(name = "jobStatus_id", referencedColumnName = "id")
	private JobStatus jobStatus;
	
	@ManyToOne
	@JoinColumn(name = "jobType_id", referencedColumnName = "id")
	private JobType jobType;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;
	
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "job")
	private List<JobActivity> jobActivity;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "job")
	private List<JobTracking> jobTracking;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "job")
	private List<Printing> printing;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "job")
	private List<Prepress> prepress;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "job")
	private List<Finishing> finishing;
	
	

}
