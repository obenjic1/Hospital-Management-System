package com.ppp.user.model;

import java.util.Collection;
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


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ppp.billing.model.Department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "confirm_password", nullable = false)
	private String confirmPassword;
	
	@Column(name = "mobile", nullable = false)
	private String mobile;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "username",nullable = false, unique = true)
	private String username;
	
	@Column(name = "image_path")
	private String imagePath;
	
	@Column(name = "connected")
	private boolean connected = Boolean.TRUE;

	@Column(name = "active")
	private boolean active = Boolean.FALSE;
	
	@Column(name = "created_at")
	private Date createdAt ;
	
	@Column(name = "reset_password_token")
	private String resetPasswordToken;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "groupe_id")
	private  Groupe groupe;
	
	@OneToMany
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private List<History> history;
	
	@ManyToOne
	@JoinColumn(name="department_id", nullable=false)
	private Department department;


	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}



}
