package com.bookstoreswing.ui.windows;

import javax.swing.*;
import java.awt.*;
import com.bookstoreswing.ui.components.HeaderPanel;
import com.bookstoreswing.ui.panels.BooksPanel;
import com.bookstoreswing.app.MainApp;
import com.bookstoreswing.utils.ImageLoader;

public class BookWindow extends JFrame {
    
    private final Color OVERLAY_COLOR = new Color(20, 10, 10, 180);

    public BookWindow() {
        setTitle("Antiquarian - Books Collection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        // Load background
        Image bg = ImageLoader.loadBackgroundImage(getWidth(), getHeight());

        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bg != null) {
                    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                }
                g.setColor(OVERLAY_COLOR);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setOpaque(false);
        setContentPane(backgroundPanel);

        // Header
        setupHeader(backgroundPanel);

        // Books panel
        BooksPanel booksPanel = new BooksPanel(MainApp.CART);
        JScrollPane scrollPane = new JScrollPane(booksPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void setupHeader(JPanel backgroundPanel) {
        HeaderPanel header = new HeaderPanel("Antiquarian");
        header.setActivePage("Books");
        
        // Navigation listeners
        header.addHomeListener(e -> navigateToHome());
        header.addBooksListener(e -> {}); // Already on books page
        header.addFavoriteListener(e -> navigateToFavorites());
        header.addCartListener(e -> navigateToCart());
        
        backgroundPanel.add(header, BorderLayout.NORTH);
    }

    private void navigateToHome() {
        dispose();
        SwingUtilities.invokeLater(() -> new HomeWindow().setVisible(true));
    }

    private void navigateToFavorites() {
        dispose();
        SwingUtilities.invokeLater(() -> new FavoriteWindow(MainApp.FAVORITES).setVisible(true));
    }

    private void navigateToCart() {
        dispose();
        SwingUtilities.invokeLater(() -> new CartPage(MainApp.CART).setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookWindow().setVisible(true));
    }
}