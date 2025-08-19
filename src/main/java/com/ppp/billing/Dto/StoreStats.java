package com.ppp.billing.Dto;

import java.math.BigDecimal;

public class StoreStats {
	    private long totalMedicines;
	    private long totalQuantity;
	    private BigDecimal totalValue;
	    private long expiringSoon;

	    // constructors, getters, setters

	    public StoreStats() {}

	    public StoreStats(long totalMedicines, long totalQuantity, BigDecimal totalValue, long expiringSoon) {
	        this.totalMedicines = totalMedicines;
	        this.totalQuantity = totalQuantity;
	        this.totalValue = totalValue;
	        this.expiringSoon = expiringSoon;
	    }

	    public long getTotalMedicines() { return totalMedicines; }
	    public void setTotalMedicines(long totalMedicines) { this.totalMedicines = totalMedicines; }
	    public long getTotalQuantity() { return totalQuantity; }
	    public void setTotalQuantity(long totalQuantity) { this.totalQuantity = totalQuantity; }
	    public BigDecimal getTotalValue() { return totalValue; }
	    public void setTotalValue(BigDecimal totalValue) { this.totalValue = totalValue; }
	    public long getExpiringSoon() { return expiringSoon; }
	    public void setExpiringSoon(long expiringSoon) { this.expiringSoon = expiringSoon; }
	


}
