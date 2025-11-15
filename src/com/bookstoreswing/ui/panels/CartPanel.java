package com.bookstoreswing.ui.panels;
import javax.swing.*;
import java.awt.*;

public class CartPanel extends JPanel {
    public CartPanel() {
        setOpaque(false);
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Cart (Panier)", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Serif", Font.BOLD, 28));
        add(title, BorderLayout.NORTH);

        JLabel info = new JLabel("<html><div style='text-align:center; color:#f3e8de;'>Your cart is empty.</div></html>", SwingConstants.CENTER);
        info.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(info, BorderLayout.CENTER);
    }
}
