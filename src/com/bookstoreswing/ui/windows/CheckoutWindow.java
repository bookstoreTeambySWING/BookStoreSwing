package com.bookstoreswing.ui.windows;

import javax.swing.*;
import java.awt.*;
import com.bookstoreswing.service.CartService;
import com.bookstoreswing.model.User;

/**
 * Checkout form to enter customer info and confirm order
 */
public class CheckoutWindow extends JFrame {
    private CartService cartService;

    public CheckoutWindow(CartService cs){
        this.cartService = cs;
        setTitle("Checkout");
        setSize(450,300);
        setLocationRelativeTo(null);
        initUI();
        setVisible(true);
    }

    private void initUI(){
        setLayout(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(4,2,6,6));
        JTextField name = new JTextField();
        JTextField phone = new JTextField();
        JTextArea address = new JTextArea(3,20);

        form.add(new JLabel("Name:")); form.add(name);
        form.add(new JLabel("Phone:")); form.add(phone);
        form.add(new JLabel("Address:")); form.add(new JScrollPane(address));
        add(form, BorderLayout.CENTER);

        JButton confirm = new JButton("Confirm Order");
        confirm.addActionListener(e -> {
            String nm = name.getText().trim();
            String ph = phone.getText().trim();
            String addr = address.getText().trim();
            User u = new User("guest", nm, addr);
            boolean ok = new com.bookstoreswing.ui.controllers.CheckoutController().processOrder(u, cartService.getItems());
            if(ok){
                cartService.clear();
                JOptionPane.showMessageDialog(this, "Thank you! Order placed.");
                dispose();
            }
        });

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(confirm);
        add(bottom, BorderLayout.SOUTH);
    }
}
