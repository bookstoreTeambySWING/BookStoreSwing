package com.bookstoreswing.model;

import java.util.List;

/**
 * Represents an order
 */
public class Order {
    private String orderId;
    private User customer;
    private List<CartItem> items;
    private double total;

    public Order(String orderId, User customer, List<CartItem> items) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = items;
        this.total = calculateTotal();
    }

    private double calculateTotal(){
        double s = 0;
        for(CartItem it : items) s += it.getTotal();
        return s;
    }

    public String getOrderId(){ return orderId; }
    public User getCustomer(){ return customer; }
    public List<CartItem> getItems(){ return items; }
    public double getTotal(){ return total; }
}
