package com.bookstoreswing.ui.components;

import javax.swing.*;
import java.awt.*;

/**
 * Header with simple title and a search field similar to design
 */
public class HeaderPanel extends JPanel {
    public HeaderPanel(String title){
        setLayout(new BorderLayout());
        setBackground(new Color(86, 57, 57)); // warm dark maroon
        setBorder(BorderFactory.createEmptyBorder(8,12,8,12));

        JLabel lbl = new JLabel(title);
        lbl.setFont(new Font("Serif", Font.BOLD, 20));
        lbl.setForeground(new Color(246,236,220));
        lbl.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
        add(lbl, BorderLayout.WEST);

        // center search bar
        JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
        center.setOpaque(false);
        JTextField search = new JTextField(30);
        search.setPreferredSize(new Dimension(360, 26));
        search.setText("Search for book, author, theme");
        center.add(search);
        add(center, BorderLayout.CENTER);

        // small icons on right
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        right.setOpaque(false);
        JLabel home = new JLabel("Home");
        JLabel books = new JLabel("Books");
        JLabel fav = new JLabel("Favorite");
        right.add(home); right.add(books); right.add(fav);
        add(right, BorderLayout.EAST);
    }
}
