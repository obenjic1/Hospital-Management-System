package com.ppp.billing.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"visits"})
@Table(name = "consultation_subtype")
public class ConsultationSubtype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; 

    private BigDecimal price;
    
    @ManyToMany(mappedBy = "subtypes")
    private List<Visit> visits = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "consultation_type_id")
    @JsonIgnore
    private ConsultationType consultationType;
}