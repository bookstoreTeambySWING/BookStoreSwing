package com.bookstoreswing.ui.controllers;

import com.bookstoreswing.service.CartService;
import javax.swing.*;

/**
 * Controller to manage Cart window actions
 */
public class CartController {
    private CartService cartService;
    private JFrame parent;

    public CartController(CartService cs, JFrame parent){
        this.cartService = cs;
        this.parent = parent;
    }

    public void openCartWindow(){
        com.bookstoreswing.ui.windows.CartWindow w = new com.bookstoreswing.ui.windows.CartWindow(cartService);
        w.setVisible(true);
    }
}
