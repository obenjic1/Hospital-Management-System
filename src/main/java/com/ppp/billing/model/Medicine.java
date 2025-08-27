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

@Entity
@Table(name="medicines")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Medicine {
	
	
	public enum Location {
        STORE,
        PHARMACY
    };
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String name;
    private String description;
    private BigDecimal price;
    private int threshold;
    private int quantity;
    private int storeQuantity;
    private int pharmacyQuantity;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private  LocalDate expirationDate;
    private boolean lowStock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    @Enumerated(EnumType.STRING)
    private Location location;
    
    
  public void setLowStock() {
	  
	  if(quantity<=threshold) {
		  lowStock=true;
		  
	  }else {
		  lowStock=false;

	  }
  }
  
  @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Tracking> tracking = new ArrayList<>();

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
    
}