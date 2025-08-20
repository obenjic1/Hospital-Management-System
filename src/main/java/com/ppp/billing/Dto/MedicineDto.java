package com.ppp.billing.Dto;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.format.annotation.DateTimeFormat;

import com.ppp.billing.model.Medicine.Location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDto {
	
		private String name;
	    private String description;
	    private BigDecimal price;
	    private int threshold;
	    private int quantity;
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    private LocalDate expiringDate;
	   
	    private Long category;
	    @Enumerated(EnumType.STRING)
	    private Location location;	    


	
	  

}
