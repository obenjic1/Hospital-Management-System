package com.ppp.user.model;

import java.time.LocalDate;
import java.util.Collection;
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

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE user SET deleted = true WHERE id=?")
@FilterDef(name = "deletedUsertFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedUsertFilter", condition = "deleted = :isDeleted")
@Entity(name = "user")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	@Column(nullable = false, length = 255)
	private String firstName;	
	@Column(nullable = false, length = 255)
	private String lastName;
	@Column(nullable = false, unique = true, length = 255)
	private String email;
	@Column(nullable = false, length = 255)
	private String password;
	@Column(nullable = false, length = 15)
	private String mobile;
	@Column(nullable = false, length = 255)
	private String address;
	@Column(nullable = false, unique = true, length = 255)
	private String username;	
	private String imagePath;	
	private boolean connected = Boolean.FALSE;
	private boolean deleted = Boolean.FALSE;
	private LocalDate createdAt ;
	
	private String resetPasswordToken;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "groupe_id")
	private  Groupe groupe;
	
	@OneToMany
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private List<History> history;
	

	
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
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}



}
