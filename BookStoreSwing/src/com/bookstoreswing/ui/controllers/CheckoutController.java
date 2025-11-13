package com.bookstoreswing.ui.controllers;

import javax.swing.*;
import com.bookstoreswing.service.*;
import com.bookstoreswing.model.*;
import com.bookstoreswing.utils.Validator;

/**
 * Controller for checkout process
 */
public class CheckoutController {
    private OrderService orderService = new OrderService();

    public boolean processOrder(User customer, java.util.List<CartItem> items){
        if(!com.bookstoreswing.utils.Validator.validateCustomer(customer)){
            JOptionPane.showMessageDialog(null, "Invalid customer data");
            return false;
        }
        Order o = OrderService.createOrder(customer, items);
        JOptionPane.showMessageDialog(null, "Order saved. Total: " + o.getTotal() + " DA");
        return true;
    }
}
