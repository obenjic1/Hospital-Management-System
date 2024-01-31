package com.ppp.user.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "history")
public class History {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@ManyToOne(fetch = FetchType.EAGER, optional = false)
//	@JoinColumn(name = "user_id")
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	private User user;
	
	private Date createdAt = new Date();

}
