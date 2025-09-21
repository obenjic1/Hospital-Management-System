package com.ppp.billing.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="medicines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {

    public enum Location {
        STORE,
        PHARMACY
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    private String description;

    /** Selling price per unit */
    private BigDecimal unitPrice;

    /** Selling price per packet */
    private BigDecimal packetPrice;

    /** Buying (purchase) price per packet */
    private BigDecimal purchasePrice;

    /** How many units in one packet (e.g., 10 tablets per pack) */
    private int unitsPerPacket;

    private int threshold;
    private int quantity;          // total quantity in stock
    private int storeQuantity;
    private int pharmacyQuantity;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate expirationDate;

    private boolean lowStock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Enumerated(EnumType.STRING)
    private Location location;

    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tracking> tracking = new ArrayList<>();

    public void setLowStock() {
        this.lowStock = quantity <= threshold;
    }

    // helper method to add history
    public void addTracking(String action, String description) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        Tracking t = new Tracking();
        t.setAction(action);
        t.setDescription(description);
        t.setPerformedBy(userName);
        t.setCreationDate(LocalDateTime.now());
        t.setMedicine(this);
        this.tracking.add(t);
    }

    /** 
     * Utility methods for handling stock 
     */
    public void addStock(int packets, int units) {
        int totalUnits = packets * unitsPerPacket + units;
        this.quantity += totalUnits;
        this.storeQuantity += totalUnits;
        setLowStock();
    }

    public void deductStock(int packets, int units) {
        int totalUnits = packets * unitsPerPacket + units;
        this.quantity -= totalUnits;
        this.pharmacyQuantity -= totalUnits;
        setLowStock();
    }
}

