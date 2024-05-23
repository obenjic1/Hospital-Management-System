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
@Entity(name = "job_activity")
public class JobActivity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "glueing_option")
	private String glueOption;
	
	@Column(name = "binding_type")
	private String bindingType;
	
	@Column(name = "x_perforated")
	private int xPerforated;
	
	@Column(name = "x_numbered")
	private int xNumbered;
	
	@Column(name = "x_creased")
	private int xCreased;
	
	@Column(name = "lamination")
	private int lamination;
	
	@Column(name = "x_wired_stiched")
	private int xWiredStiched;
	
	@Column(name = "x_cross")
	private int xCross;
	
	@Column(name = "is_sewn")
	private boolean isSewn = Boolean.FALSE;
	
	@Column(name = "is_selloptaped")
	private boolean isSelloptaped = Boolean.FALSE;
	
	@Column(name = "is_handgather")
	private boolean isHandgather = Boolean.FALSE;
	
	@Column(name = "is_stitching")
	private boolean isStitching = Boolean.FALSE;
	
	@Column(name = "is_trimmed")
	private boolean isTrimmed = Boolean.FALSE;
	
	@ManyToOne
	@JoinColumn(name = "job_id", referencedColumnName = "id")
	private Job job;
	
	
}
