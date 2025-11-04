package com.ppp.billing.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.format.annotation.DateTimeFormat;

import com.ppp.billing.model.Medicine;
import com.ppp.billing.model.Medicine.Location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDto {

	private long id;
    private String name;
    private String description;

    /** Selling price per unit */
    private BigDecimal unitPrice;

    /** Selling price per packet */
    private BigDecimal packetPrice;

    /** Buying (purchase) price per packet */
    private BigDecimal purchasePrice;

    /** Number of units in one packet */
    private int unitsPerPacket;

    private int threshold;
    private int quantity;
    private String code;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expiringDate;

    private Long category;

    @Enumerated(EnumType.STRING)
    private Location location;
    
    
  
    // Constructor that takes a Medicine entity
    public MedicineDto(Medicine m) {
        this.id = m.getId();
        this.name = m.getName();
        this.description = m.getDescription();
        this.unitPrice = m.getUnitPrice();
        this.packetPrice = m.getPacketPrice();
        this.purchasePrice = m.getPurchasePrice();
        this.unitsPerPacket = m.getUnitsPerPacket();
        this.threshold = m.getThreshold();
        this.quantity = m.getQuantity();
        this.code = m.getCode();
        this.expiringDate = m.getExpirationDate();
        this.category = m.getCategory() != null ? m.getCategory().getId() : null;
    }
}

