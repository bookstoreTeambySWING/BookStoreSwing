package com.bookstoreswing.service;

import java.util.UUID;
import com.bookstoreswing.model.*;
import com.bookstoreswing.utils.FileManager;

/**
 * Handles order creation and saving
 */
public class OrderService {
    public static Order createOrder(User customer, java.util.List<CartItem> items){
        String id = UUID.randomUUID().toString();
        Order o = new Order(id, customer, items);
        FileManager.appendOrderToFile(o); // MAINTENANT ÇA DEVRAIT MARCHER
        return o;
    }
}