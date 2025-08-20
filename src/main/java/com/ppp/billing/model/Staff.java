package com.ppp.billing.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ppp.user.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "staff")
@Data                  
@NoArgsConstructor     
@AllArgsConstructor 
public class Staff {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private boolean isActive = true;

    private String address;
    private String speciality;
    private String gender;
    private String phone;
    private String email;
    private LocalDate hireDate = LocalDate.now();
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

   

    // If this staff has a login, it will be linked to User
    @OneToOne(mappedBy = "staff", cascade = CascadeType.ALL)
    private User user;


}
