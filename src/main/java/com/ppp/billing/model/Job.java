package com.ppp.billing.model;

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
	@Column(nullable = false)
	private String title;
	@Column(nullable = false, name="reference_number")
	private String referenceNumber;
	@Column()
	private String description;
	@Column(name="content_volume")
	private int contentVolume;
	@Column(name="cover_volume")
	private int coverVolume;
	@Column(name="total_content_signature")
	private int totalContentSignature;
	@Column(name="total_cover_signature")
	private int totalCoverSignature;
	
	@Column(name="existing_plate", columnDefinition="boolean default false")
	private boolean existingPlate;
	
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
	
	@ManyToOne
	@JoinColumn(name = "binding_type_id", referencedColumnName = "id")
	private BindingType bindingType;
	
	@ManyToOne
	@JoinColumn(name = "job_status_id", referencedColumnName = "id")
	private JobStatus jobStatus;
	
	@ManyToOne
	@JoinColumn(name = "job_type_id", referencedColumnName = "id")
	private JobType jobType;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "job")
	private List<JobActivity> jobActivities;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "job")
	private List<JobTracking> jobTrackings;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "job")
	private List<PrintingPricing> printingPricings;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "job")
	private List<PrepressPricing> prepressPricings;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "job")
	private List<FinishingPricing> finishingPricings;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "job")
	private List<JobPaper> jobPapers;

	
	

}
