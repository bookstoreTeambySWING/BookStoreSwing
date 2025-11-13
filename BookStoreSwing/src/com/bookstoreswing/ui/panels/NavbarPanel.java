package com.bookstoreswing.ui.panels;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NavbarPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel contentCards;

    public NavbarPanel(CardLayout cardLayout, JPanel contentCards) {
        this.cardLayout = cardLayout;
        this.contentCards = contentCards;
        initUI();
    }

    private void initUI() {
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 18));
        setLayout(new BorderLayout());

        Color mainColor = new Color(0xA6615A);
        Color cream = new Color(0xEADFC9);

        // Left: shop name (acts like a home button)
        JLabel nameLabel = new JLabel("Antiquarian");
        nameLabel.setForeground(cream);
        nameLabel.setFont(new Font("Serif", Font.BOLD, 22));
        nameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nameLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(contentCards, "HOME");
            }
        });

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        left.setOpaque(false);
        left.add(nameLabel);

        // Center: search field
        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        JTextField searchField = new JTextField(30);
        searchField.setText("Search for book, author, theme");
        searchField.setForeground(Color.LIGHT_GRAY);
        searchField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search for book, author, theme")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search for book, author, theme");
                    searchField.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
        JButton searchBtn = new JButton("\uD83D\uDD0D"); // magnifier unicode
        searchBtn.setFocusPainted(false);
        searchBtn.addActionListener(e -> {
            // placeholder search action
            JOptionPane.showMessageDialog(this, "Search: " + searchField.getText());
        });
        center.add(searchField);
        center.add(searchBtn);

        // Right: nav items
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 18, 0));
        right.setOpaque(false);

        String[] names = {"Home", "Books", "Favorite", "Cart"};
        String[] cards = {"HOME", "BOOKS", "FAVORITE", "CART"};

        for (int i = 0; i < names.length; i++) {
            String nm = names[i];
            String card = cards[i];
            JLabel lbl = new JLabel(nm);
            lbl.setForeground(cream);
            lbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
            lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lbl.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cardLayout.show(contentCards, card);
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    lbl.setForeground(Color.WHITE);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    lbl.setForeground(cream);
                }
            });
            right.add(lbl);
        }

        // Dock panel with background color behind navbar items
        JPanel dock = new JPanel(new BorderLayout());
        dock.setBackground(new Color(0,0,0,120));
        dock.setOpaque(false);

        add(left, BorderLayout.WEST);
        add(center, BorderLayout.CENTER);
        add(right, BorderLayout.EAST);

        // Add a rounded panel background appearance by painting
        setPreferredSize(new Dimension(0, 70));
    }

}
