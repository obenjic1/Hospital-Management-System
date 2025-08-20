
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
@Entity(name="paper_type")
public class PaperType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	
	@Column(name = "jobPapers")
	@OneToMany(fetch = FetchType.LAZY,  mappedBy = "paperType")
	private List<JobPaper> jobPapers;

}
