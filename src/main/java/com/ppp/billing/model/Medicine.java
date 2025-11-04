package com.ppp.billing.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="medicines")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"tracking", "category"})

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
    private String code;

    /** Selling price per unit */
    private BigDecimal unitPrice;

    /** Selling price per packet */
    private BigDecimal packetPrice;

    /** Buying (purchase) price per packet */
    private BigDecimal purchasePrice;

    /** How many units in one packet (e.g., 10 tablets per pack) */
    private int unitsPerPacket;

    private int totalUnitsPhamarcy;
    private int totalUnitsStore;
    private int totalUnitsQuantity;

    private int unitLeftPhamarcy;
    private int threshold;
    private int quantity;          
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

    // -------------------------------------------------------------------------
    // Utility and Business Logic
    // -------------------------------------------------------------------------

    public void setLowStock() {
        this.lowStock = this.getQuantity() <= threshold;
    }

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

    // -----------------------------
    // Safe Getters for Derived Values
    // -----------------------------

    public int getQuantity() {
        return (unitsPerPacket <= 0) ? 0 : totalUnitsQuantity / unitsPerPacket;
    }

    public int getStoreQuantity() {
        return (unitsPerPacket <= 0) ? 0 : totalUnitsStore / unitsPerPacket;
    }

    public int getPharmacyQuantity() {
        return (unitsPerPacket <= 0) ? 0 : totalUnitsPhamarcy / unitsPerPacket;
    }

    public int getPacketsInStore() {
        return (unitsPerPacket <= 0) ? 0 : totalUnitsStore / unitsPerPacket;
    }

    public int getUnitsInStore() {
        return (unitsPerPacket <= 0) ? 0 : totalUnitsStore % unitsPerPacket;
    }

    public int getUnitLeftPhamarcy() {
        return (unitsPerPacket <= 0) ? 0 : totalUnitsPhamarcy % unitsPerPacket;
    }
    
    public int getPacketsInPharmacy() {
        return (unitsPerPacket <= 0) ? 0 : totalUnitsStore / unitsPerPacket;
    }
    // -------------------------------------------------------------------------
    // Stock Manipulation Methods
    // -------------------------------------------------------------------------
    
    
    public void tranferToPharmacy (int qty) {
    	this.storeQuantity -= qty;
    	this.pharmacyQuantity +=qty;
    	this.totalUnitsStore = this.getTotalUnitsStore() - (qty * this.unitsPerPacket);
    	this.totalUnitsPhamarcy = this.getTotalUnitsPhamarcy() + (qty * this.unitsPerPacket);
    	
    	
    }

    /** Called after all fields (unitsPerPacket, quantity, etc.) are set */
    public void updateTotals() {
        this.totalUnitsQuantity = this.unitsPerPacket * this.quantity;
        this.totalUnitsStore = this.totalUnitsQuantity;
       // this.totalUnitsPhamarcy = 0;
        this.lowStock = this.getQuantity() <= threshold;
    }

    /** Called when editing the existing quantity */
    public void editQuantity(int qty) {
        this.quantity = qty;
        this.totalUnitsQuantity = this.unitsPerPacket * qty;
        this.totalUnitsStore = this.totalUnitsQuantity - this.totalUnitsPhamarcy;
        this.lowStock = this.getQuantity() <= threshold;
    }

    /** Called when adding stock (in packets) */
    public void addQuantity(int qty) {
        this.totalUnitsQuantity += this.unitsPerPacket * qty;
        this.totalUnitsStore = this.totalUnitsQuantity - this.totalUnitsPhamarcy;
        this.quantity += qty;
        this.lowStock = this.getQuantity() <= threshold;

        
    }

    public void setTotalUnitsPhamarcy() {
        this.totalUnitsPhamarcy = this.unitsPerPacket * this.pharmacyQuantity;
        this.totalUnitsStore = this.totalUnitsQuantity - this.totalUnitsPhamarcy;
        this.unitLeftPhamarcy = this.totalUnitsPhamarcy % this.unitsPerPacket;
    }

    // -------------------------------------------------------------------------
    // Sales Logic
    // -------------------------------------------------------------------------

    public BigDecimal sellUnits(int unitsRequested) {
        if (unitsRequested <= 0)
            throw new IllegalArgumentException("Units must be > 0");

        if (this.totalUnitsPhamarcy < unitsRequested)
            throw new IllegalStateException("Insufficient pharmacy stock – request transfer");

        this.totalUnitsPhamarcy -= unitsRequested;
        this.totalUnitsQuantity -= unitsRequested;
        setLowStock();
        addTracking("SALE", "Sold " + unitsRequested + " unit(s) @ " + unitPrice + " per unit");

        return unitPrice.multiply(BigDecimal.valueOf(unitsRequested));
    }

    public BigDecimal sellPackets(int packetsRequested) {
        if (packetsRequested <= 0)
            throw new IllegalArgumentException("Packets must be > 0");

        if (this.totalUnitsPhamarcy / this.unitsPerPacket < packetsRequested && this.totalUnitsPhamarcy > 0) {
            throw new IllegalStateException("Packets are finished in the pharmacy, only " 
                    + this.totalUnitsPhamarcy + " units left");
        }

        if (this.totalUnitsPhamarcy / this.unitsPerPacket < packetsRequested && this.totalUnitsPhamarcy < 1) {
            throw new IllegalStateException("Insufficient pharmacy stock – request transfer");
        }

        this.totalUnitsPhamarcy -= packetsRequested * this.unitsPerPacket;
        this.totalUnitsQuantity -= packetsRequested * this.unitsPerPacket;
        setLowStock();
        addTracking("SALE", "Sold " + packetsRequested + " packet(s) @ " + packetPrice + " each");

        return packetPrice.multiply(BigDecimal.valueOf(packetsRequested));
    }

	
    
}

