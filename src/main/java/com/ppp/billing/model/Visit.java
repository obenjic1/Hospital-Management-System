package com.ppp.billing.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;
    
    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff attendingStaff;

    private LocalDate visitDate;
    private LocalTime visitTime;

    @OneToMany(mappedBy = "visit", cascade = CascadeType.ALL)
    private List<VisitService> services;

    @Enumerated(EnumType.STRING)
    private VisitReason reason;
    
    @OneToOne(mappedBy = "visit", cascade = CascadeType.ALL)
    private Facture facture;
}
