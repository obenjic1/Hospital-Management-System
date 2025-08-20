package com.ppp.billing.model;
import java.util.Date;
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
@Entity(name="printing_machine")
public class PrintingMachine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, unique = true, length = 255)
	private String name;
	
	@Column
	private String abbreviation;
	
	@Column(name="plate_length")
	private int plateLength; 
	
	@Column(name="plate_width")
	private int plateWidth; 
	
	@Column(columnDefinition="boolean default true")
	private boolean active ;
	
	@Column
	private String thumbnail;
	
	@Column(name="creation_date")
	private Date creationDate;

}
 