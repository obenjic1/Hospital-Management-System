package com.ppp.billing.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Cart {

	private List<CartItem> items = new ArrayList<>();

    // Add an item to the cart
    public void addItem(CartItem item) {
        // If the medicine already exists, just update quantity
        for (CartItem existing : items) {
            if (existing.getMedicineId().equals(item.getMedicineId())) {
                existing.setQuantity(existing.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }

    // Remove an item by medicineId
    public void removeItem(Long medicineId) {
        items.removeIf(item -> item.getMedicineId().equals(medicineId));
    }

    // Calculate the total cost of all items
    public BigDecimal getGrandTotal() {
        return items.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Clear the cart
    public void clear() {
        items.clear();
    }
}
