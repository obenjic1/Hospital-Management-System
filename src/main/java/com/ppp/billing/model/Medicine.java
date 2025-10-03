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
    
    
    private String code;


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
    public BigDecimal sellUnits(int unitsRequested) {
        if (unitsRequested <= 0)
            throw new IllegalArgumentException("Units must be > 0");

        int packetsNeeded = (unitsRequested + unitsPerPacket - 1) / unitsPerPacket;
        if (this.pharmacyQuantity < packetsNeeded)
            throw new IllegalStateException("Insufficient pharmacy stock – request transfer");

        this.pharmacyQuantity -= packetsNeeded;
        this.quantity           -= packetsNeeded;
        setLowStock();

        addTracking("SALE", "Sold " + unitsRequested + " unit(s)  (used "
                    + packetsNeeded + " packet(s)) @ " + unitPrice + " per unit");
        return unitPrice.multiply(BigDecimal.valueOf(unitsRequested));
    }

    /* ----------------------------------------------------------
     * 2.  sell whole packets – pharmacy ONLY
     * ---------------------------------------------------------- */
    public BigDecimal sellPackets(int packetsRequested) {
        if (packetsRequested <= 0)
            throw new IllegalArgumentException("Packets must be > 0");
        if (this.pharmacyQuantity < packetsRequested)
            throw new IllegalStateException("Insufficient pharmacy stock – request transfer");

        this.pharmacyQuantity -= packetsRequested;
        this.quantity           -= packetsRequested;
        setLowStock();

        addTracking("SALE", "Sold " + packetsRequested + " packet(s) @ " + packetPrice + " each");
        return packetPrice.multiply(BigDecimal.valueOf(packetsRequested));
    }
    
}

