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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private long id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "status")
	private String status;
	
	@Column(name="reference_number")
	private String referenceNumber;
	
	@Column(name = "content_volume")
	private int contentVolume;
	
	@Column(name = "cover_volume")
	private int coverVolume;

	@Column(name = "open_width")
	private double openWidth;
	
	@Column(name = "open_length")
	private double openLength;
	
	@Column(name = "close_length")
	private double closeLength;
	
	@Column(name = "close_width")
	private double closeWidth;
	
	@Column(name = "ctp_fees")
	private int ctpFees;
	
	@Column(name = "variable_cost", nullable = true, columnDefinition = "float default 0")
	private float variableCost;
	
	@Column(name = "fix_cost", nullable = true, columnDefinition = "float default 0")
	private float fixCost;
	
	@Column(name="existing_plate", columnDefinition="boolean default false")
	private boolean existingPlate;
	
	@Column(name="typesetting_by_us", columnDefinition="boolean default true")
	private boolean typesettingByUs;
	
	@Column(name="data_supply_by_customer", columnDefinition="boolean default true")
	private boolean dataSuppliedByCustomer;
	
	@Column(name="layout_by_us", columnDefinition="boolean default true")
	private boolean layOutByUs;
	
	@Column(name="ready_to_print_date")
	@Temporal(TemporalType.DATE)
	private Date readytoPrintDate;
	
	@Column(name="expected_delivery_date")
	@Temporal(TemporalType.DATE)
	private Date expectedDeliveryDate;
	
	@Column(name="actual_delivery_date")
	@Temporal(TemporalType.DATE)
	private Date actualDeliveryDate;
	
	@Column(name="creation_date")
	private Date creationDate;
	
	@OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "job_activity_id", referencedColumnName = "id")
	private JobActivity jobActivity;
	
	@Column(name="job_papers")
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "job", cascade = CascadeType.PERSIST)
	private List<JobPaper> jobPapers;
	
	@ManyToOne
	@JoinColumn(name = "job_type_id", referencedColumnName = "id")
	private JobType jobType;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;
	
	

}
