package com.ppp.billing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sf.saxon.functions.ConstantFunction.True;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "job_activity")
public class JobActivity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
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
	
	@Column(name = "hand_folding_cov", nullable = true, columnDefinition = "int default 0")
	private int handFoldingCov;
	
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
	
	@OneToOne(mappedBy = "jobActivity")
	private Job job;
	
	@ManyToOne
	@JoinColumn(name = "binding_type_id", referencedColumnName = "id")
	private BindingType bindingType;
	
	

	
	
}
