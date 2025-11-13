package com.bookstoreswing.ui.components;

import com.bookstoreswing.model.Book;
import com.bookstoreswing.service.CartService;

import javax.swing.*;
import java.awt.*;

/**
 * Simple stylized card for a Book used in the home featured grid.
 * Assumes your Book model has: getTitle(), getAuthor(), getPrice()
 */
public class BookCardPanel extends JPanel {

    public BookCardPanel(Book book, CartService cartService) {
        setLayout(new BorderLayout());
        setOpaque(false);
        setPreferredSize(new Dimension(180, 320));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(140, 100, 95), 2),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        // COVER area (placeholder drawing)
        JPanel cover = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // background of cover
                g.setColor(new Color(90, 60, 60));
                g.fillRect(0, 0, getWidth(), getHeight());
                // optional faux artwork: draw title initials
                g.setColor(new Color(235, 220, 200));
                g.setFont(new Font("Serif", Font.BOLD, 18));
                String t = book.getTitle() == null ? "Book" : book.getTitle();
                String initials = t.length() >= 2 ? t.substring(0, 2).toUpperCase() : t.toUpperCase();
                g.drawString(initials, 12, 26);
            }
        };
        cover.setPreferredSize(new Dimension(160, 200));
        cover.setOpaque(false);
        cover.setLayout(null);

        // heart/favorite button (top-right)
        JButton heart = new JButton("\u2661"); // empty heart
        heart.setToolTipText("Add to favourites");
        heart.setBorderPainted(false);
        heart.setContentAreaFilled(false);
        heart.setForeground(new Color(220, 200, 180));
        heart.setBounds(118, 6, 36, 26);
        heart.addActionListener(e -> {
            heart.setText("\u2665");
            JOptionPane.showMessageDialog(this, book.getTitle() + " added to favourites");
        });
        cover.add(heart);

        add(cover, BorderLayout.NORTH);

        // Title and author
        JPanel mid = new JPanel(new GridLayout(2, 1));
        mid.setOpaque(false);
        JLabel title = new JLabel("<html><div style='text-align:center;'>" + safeString(book.getTitle()) + "</div></html>");
        title.setFont(new Font("Serif", Font.BOLD, 13));
        title.setForeground(new Color(245, 235, 220));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel author = new JLabel(safeString(book.getAuthor()), SwingConstants.CENTER);
        author.setFont(new Font("SansSerif", Font.PLAIN, 12));
        author.setForeground(new Color(210, 190, 170));

        mid.add(title);
        mid.add(author);
        mid.setBorder(BorderFactory.createEmptyBorder(8, 4, 8, 4));
        add(mid, BorderLayout.CENTER);

        // bottom: price + add-to-cart
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);
        JLabel price = new JLabel(formatPrice(book.getPrice()));
        price.setFont(new Font("Serif", Font.BOLD, 13));
        price.setForeground(new Color(235, 215, 190));
        price.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));

        JButton add = new JButton("Add");
        add.setBackground(new Color(120, 78, 76));
        add.setForeground(Color.WHITE);
        add.setFocusPainted(false);
        add.addActionListener(e -> {
            if (cartService != null) {
                cartService.addBook(book);
                JOptionPane.showMessageDialog(this, book.getTitle() + " added to cart");
            } else {
                JOptionPane.showMessageDialog(this, "Cart service unavailable");
            }
        });

        bottom.add(price, BorderLayout.WEST);
        bottom.add(add, BorderLayout.EAST);
        bottom.setBorder(BorderFactory.createEmptyBorder(6, 4, 4, 4));
        add(bottom, BorderLayout.SOUTH);
    }

    private String safeString(String s) {
        return s == null ? "" : s;
    }

    private String formatPrice(Object price) {
        if (price == null) return "—";
        return price.toString();
    }
}
