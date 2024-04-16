package com.ppp.user.model;

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
@Entity(name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 255)
	private String name;
	@Column(nullable = false, length = 255)
	private String email;
	@Column(nullable = false, length = 10)
	private String telephone;
	@Column(nullable = false, length = 255)
	private String address;
	private String thumbnail;
	private Date creationDate;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "customer")
	private List<Job> job;

}
