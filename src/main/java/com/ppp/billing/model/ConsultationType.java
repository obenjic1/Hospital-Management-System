package com.ppp.billing.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; 

    @OneToMany(mappedBy = "consultationType")
    private List<ConsultationSubtype> subtypes;
    
    @JsonIgnore
    @OneToMany(mappedBy = "consultationType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Visit> visits;
 

}