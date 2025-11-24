package com.bookstoreswing.ui.components;

import com.bookstoreswing.model.Book;
import com.bookstoreswing.service.CartService;
import com.bookstoreswing.app.MainApp;
import com.bookstoreswing.utils.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class BookCardPanel extends JPanel {

    public BookCardPanel(Book book, CartService cartService) {

        // ---------- CARD STYLE ----------
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(true);
        setBackground(new Color(55, 40, 35, 175));   // nicer transparent brown
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(130, 115, 105), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        setPreferredSize(new Dimension(195, 360)); // same size as screenshot


        // ---------- IMAGE CONTAINER ----------
        JPanel imageContainer = new JPanel();
        imageContainer.setOpaque(false);
        imageContainer.setLayout(new OverlayLayout(imageContainer));
        imageContainer.setMaximumSize(new Dimension(180, 250));

        String imgPath = book.getImagePath();
        if (imgPath != null && !imgPath.startsWith("assets/"))
            imgPath = "assets/" + imgPath;

        ImageIcon icon = ImageLoader.loadIcon(imgPath, 180, 250);
        JLabel coverLabel;

        if (icon != null) coverLabel = new JLabel(icon);
        else {
            coverLabel = new JLabel("?", SwingConstants.CENTER);
            coverLabel.setOpaque(true);
            coverLabel.setBackground(new Color(100, 70, 65));
            coverLabel.setForeground(Color.WHITE);
        }

        coverLabel.setAlignmentX(0.5f);
        imageContainer.add(coverLabel);


        // ---------- HEART ICON ----------
        ImageIcon emptyHeart = ImageLoader.loadIcon("assets/icons/hearticon.png", 28, 28);
        ImageIcon fullHeart  = ImageLoader.loadIcon("assets/icons/heartfilled.png", 28, 28);

        JButton heartBtn = new JButton(
                MainApp.FAVORITES.has(book) && fullHeart != null
                        ? fullHeart
                        : (emptyHeart != null ? emptyHeart : new ImageIcon())
        );

        heartBtn.setContentAreaFilled(false);
        heartBtn.setBorderPainted(false);
        heartBtn.setFocusPainted(false);
        heartBtn.setOpaque(false);

        JPanel heartPanel = new JPanel(new BorderLayout());
        heartPanel.setOpaque(false);
        heartPanel.add(heartBtn, BorderLayout.EAST);

        imageContainer.add(heartPanel);

        add(imageContainer);
        add(Box.createVerticalStrut(8));


        // ---------- TITLE ----------
        JLabel title = new JLabel(
                "<html><div style='text-align:center;'>" + book.getTitle() + "</div></html>",
                SwingConstants.CENTER
        );
        title.setForeground(new Color(245, 230, 215));
        title.setFont(new Font("Serif", Font.BOLD, 13));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(title);


        // ---------- AUTHOR ----------
        JLabel author = new JLabel(book.getAuthor(), SwingConstants.CENTER);
        author.setForeground(new Color(210, 200, 185));
        author.setFont(new Font("SansSerif", Font.PLAIN, 12));
        author.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(author);
        add(Box.createVerticalStrut(10));


        // ---------- PRICE + BUTTON ----------
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);

        JLabel price = new JLabel(String.format("%.2f €", book.getPrice() / 100.0).replace('.', ','));
        price.setForeground(new Color(240, 220, 190));
        price.setFont(new Font("Serif", Font.BOLD, 14));
        price.setBorder(BorderFactory.createEmptyBorder(6, 4, 4, 4));

        JButton addBtn = new JButton("Add to cart");
        addBtn.setFont(new Font("SansSerif", Font.PLAIN, 12));
        addBtn.setBackground(new Color(120, 78, 76));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFocusPainted(false);
        addBtn.setPreferredSize(new Dimension(90, 30));

        addBtn.addActionListener(e -> {
            cartService.addBook(book);
            JOptionPane.showMessageDialog(this, book.getTitle() + " added to cart");
        });

        bottom.add(price, BorderLayout.WEST);
        bottom.add(addBtn, BorderLayout.EAST);

        add(bottom);


        // ---------- HEART LOGIC ----------
        heartBtn.setEnabled(!MainApp.FAVORITES.has(book));

        heartBtn.addActionListener(e -> {
            MainApp.FAVORITES.add(book);
            if (fullHeart != null) heartBtn.setIcon(fullHeart);
            heartBtn.setEnabled(false);
            JOptionPane.showMessageDialog(this, book.getTitle() + " added to favorites");
        });
    }
}
