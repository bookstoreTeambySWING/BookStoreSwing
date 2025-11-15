package com.bookstoreswing.ui.windows;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.io.File;
import javax.imageio.ImageIO;

import com.bookstoreswing.ui.components.HeaderPanel;
import com.bookstoreswing.ui.panels.BooksPanel;
import com.bookstoreswing.service.CartService;

public class BookWindow extends JFrame {

    public BookWindow() {
        setTitle("Antiquarian - Books Collection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        Image bg = loadBackgroundImage();
        final Image bgFinal = bg;

        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (bgFinal != null) {
                    g.drawImage(bgFinal, 0, 0, getWidth(), getHeight(), this);

                    // SAME DARK OVERLAY AS HOMEWINDOW
                    g.setColor(new Color(20, 10, 10, 180));
                    g.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    // fallback (brown)
                    g.setColor(new Color(45, 35, 35));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };

        setContentPane(backgroundPanel);

        // NAVBAR
        HeaderPanel header = new HeaderPanel("Antiquarian");
        header.setActivePage("Books");
        backgroundPanel.add(header, BorderLayout.NORTH);

        // NAVIGATION
        header.addHomeListener(e -> {
            dispose();
            new HomeWindow().setVisible(true);
        });

        header.addBooksListener(e -> {}); // Already here

        header.addFavoriteListener(e ->
                JOptionPane.showMessageDialog(this, "Favorites coming soon.")
        );

        header.addCartListener(e ->
                JOptionPane.showMessageDialog(this, "Cart coming soon.")
        );

        // BOOK LIST PANEL
        CartService cartService = new CartService();
        BooksPanel booksPanel = new BooksPanel(cartService);

        backgroundPanel.add(booksPanel, BorderLayout.CENTER);
    }

    // ✅ SAME LOADER AS HOMEWINDOW
    private Image loadBackgroundImage() {

        String[] resourceCandidates = {
                "/assets/bg.jpg",
                "/assets/bg.jpeg",
                "/assets/bg.png",
                "/assets/bg.jpg.jpg"
        };

        for (String r : resourceCandidates) {
            try {
                URL u = getClass().getResource(r);
                if (u != null) {
                    return ImageIO.read(u);
                }
            } catch (Exception ignored) {}
        }

        // Try direct file path
        String[] fileCandidates = {
                "src/assets/bg.jpg",
                "src/assets/bg.jpeg",
                "src/assets/bg.png",
                "src/assets/bg.jpg.jpg"
        };

        for (String f : fileCandidates) {
            try {
                File file = new File(f);
                if (file.exists()) {
                    return ImageIO.read(file);
                }
            } catch (Exception ignored) {}
        }

        System.err.println("Background image NOT FOUND.");
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookWindow().setVisible(true));
    }
}
