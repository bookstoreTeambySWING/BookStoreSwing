package com.bookstoreswing.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HeaderPanel extends JPanel {

    private JButton homeBtn, booksBtn, favoriteBtn, cartBtn;
    private String currentPage;

    public HeaderPanel(String title) {

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(getWidth(), 70));
        setBackground(new Color(75, 58, 58)); // Correct navbar color
        setBorder(BorderFactory.createEmptyBorder(8, 25, 8, 25));

        // LEFT – LOGO + TITLE
        add(createLogo(title), BorderLayout.WEST);

        // CENTER – SEARCH BAR
        add(createSearchBar(), BorderLayout.CENTER);

        // RIGHT – NAVIGATION BUTTONS
        add(createNavButtons(), BorderLayout.EAST);

        setActivePage("Home");
    }

    // ---------------------------------------------------------
    // LOGO
    // ---------------------------------------------------------
    private JPanel createLogo(String title) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panel.setOpaque(false);

        JLabel icon = new JLabel("📚");
        icon.setFont(new Font("Serif", Font.PLAIN, 22));

        JLabel label = new JLabel(title);
        label.setFont(new Font("Georgia", Font.BOLD, 22));
        label.setForeground(new Color(245, 235, 220));

        panel.add(icon);
        panel.add(label);

        return panel;
    }

    // ---------------------------------------------------------
    // SEARCH BAR
    // ---------------------------------------------------------
    private JPanel createSearchBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.setOpaque(false);

        JPanel searchBox = new JPanel(new BorderLayout());
        searchBox.setPreferredSize(new Dimension(380, 38));
        searchBox.setBackground(new Color(255, 255, 255, 40));
        searchBox.setBorder(BorderFactory.createLineBorder(new Color(180, 160, 160), 1));

        JTextField field = new JTextField("Search for book, author, theme");
        field.setOpaque(false);
        field.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        field.setForeground(new Color(230, 230, 230));

        JButton searchBtn = new JButton("🔍");
        searchBtn.setFocusPainted(false);
        searchBtn.setBorder(null);
        searchBtn.setPreferredSize(new Dimension(45, 38));
        searchBtn.setBackground(new Color(0, 0, 0, 30));
        searchBtn.setForeground(Color.WHITE);

        searchBox.add(field, BorderLayout.CENTER);
        searchBox.add(searchBtn, BorderLayout.EAST);

        panel.add(searchBox);
        return panel;
    }

    // ---------------------------------------------------------
    // NAV BUTTONS
    // ---------------------------------------------------------
    private JPanel createNavButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 18, 0));
        panel.setOpaque(false);

        homeBtn = createButton("🏠 Home", "Home");
        booksBtn = createButton("📖 Books", "Books");
        favoriteBtn = createButton("❤️ Favorite", "Favorite");
        cartBtn = createButton("🛒 Cart", "Cart");

        panel.add(homeBtn);
        panel.add(booksBtn);
        panel.add(favoriteBtn);
        panel.add(cartBtn);

        return panel;
    }

    private JButton createButton(String text, String page) {

        JButton btn = new JButton(text);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        btn.setForeground(new Color(246, 236, 220));
        btn.setFont(new Font("SansSerif", Font.PLAIN, 14));

        // Hover effect
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setContentAreaFilled(true);
                btn.setBackground(new Color(110, 85, 85));
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                if (!btn.getText().startsWith("●")) {
                    btn.setContentAreaFilled(false);
                }
            }
        });

        btn.addActionListener(e -> setActivePage(page));

        return btn;
    }

    // ---------------------------------------------------------
    // ACTIVATE PAGE
    // ---------------------------------------------------------
    public void setActivePage(String page) {
        this.currentPage = page;

        resetNav(homeBtn, "🏠 Home");
        resetNav(booksBtn, "📖 Books");
        resetNav(favoriteBtn, "❤️ Favorite");
        resetNav(cartBtn, "🛒 Cart");

        JButton active = switch (page) {
            case "Home" -> homeBtn;
            case "Books" -> booksBtn;
            case "Favorite" -> favoriteBtn;
            case "Cart" -> cartBtn;
            default -> null;
        };

        if (active != null) {
            active.setText("● " + active.getText());
            active.setContentAreaFilled(true);
            active.setBackground(new Color(150, 120, 80));
            active.setBorder(BorderFactory.createLineBorder(new Color(245, 230, 200), 1));
        }
    }

    private void resetNav(JButton btn, String text) {
        btn.setText(text);
        btn.setContentAreaFilled(false);
        btn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    public String getCurrentPage() {
        return currentPage;
    }
}
