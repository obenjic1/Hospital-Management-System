package com.ppp.user.model;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "groupe")
public class Groupe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true, length = 50)
	private String name;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime createdAt;
	
	@Column(nullable = true)
	private String description;
	
	private boolean enabled = Boolean.TRUE;
	
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true,  mappedBy = "groupe")
	private List<User> users;
	
	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "groupe")
	private List<GroupeRole> groupRoles;


	@PrePersist
    public void prePersist() {
        if (createdAt == null) {
        	createdAt = LocalDateTime.now();
        }
    }
}
