package com.bookstoreswing.ui.windows;

import com.bookstoreswing.ui.components.BookCardPanel;
import com.bookstoreswing.ui.components.FooterPanel;
import com.bookstoreswing.ui.components.HeaderPanel;
import com.bookstoreswing.model.Book;
import com.bookstoreswing.service.BookService;
import com.bookstoreswing.service.CartService;
import java.util.ArrayList;
import com.bookstoreswing.data.BookData;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.List;

public class HomeWindow extends JFrame {

    private CartService cartService;

    public HomeWindow() {
        setTitle("Antiquarian - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 860);
        setLocationRelativeTo(null);

        Image bg = loadBackgroundImage();

        JPanel root = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (bg != null) {
                    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                    // overlay (so text reads well)
                    g.setColor(new Color(20, 10, 10, 130));
                    g.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    g.setColor(new Color(35, 30, 30));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        root.setOpaque(false);
        setContentPane(root);

        // Navbar
        HeaderPanel header = new HeaderPanel("Antiquarian");
        root.add(header, BorderLayout.NORTH);

        // Scrollable main content
        JPanel main = new JPanel();
        main.setOpaque(false);
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(Box.createVerticalStrut(70));
        main.add(createHeroSection());   // <- robustly centered
        main.add(Box.createVerticalStrut(65));
        main.add(createFeatured());
        main.add(Box.createVerticalStrut(50));
        main.add(new FooterPanel());
        main.add(Box.createVerticalStrut(40));

        JScrollPane scroll = new JScrollPane(main);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        root.add(scroll, BorderLayout.CENTER);
    }

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

        String[] fileCandidates = {
                "src/assets/bg.jpg",
                "src/assets/bg.jpeg",
                "assets/bg.jpg",
                "assets/bg.jpeg"
        };

        for (String f : fileCandidates) {
            try {
                File file = new File(f);
                if (file.exists()) {
                    return ImageIO.read(file);
                }
            } catch (Exception ignored) {}
        }

        System.err.println("Background image not found.");
        return null;
    }

    // --------------------------------------------------
    // HERO SECTION — robust centering using FlowLayout(CENTER)
    // --------------------------------------------------
    private JPanel createHeroSection() {

        // Outer panel with FlowLayout CENTER — guarantees horizontal centering
        JPanel outerFlow = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        outerFlow.setOpaque(false);

        // Inner hero panel (vertical) — content lives here
        JPanel hero = new JPanel();
        hero.setOpaque(false);
        hero.setLayout(new BoxLayout(hero, BoxLayout.Y_AXIS));

        // IMPORTANT: limit hero max width so it stays centered and doesn't stretch
        int heroWidth = 820; // adjust if you want narrower/wider hero
        hero.setMaximumSize(new Dimension(heroWidth, Integer.MAX_VALUE));
        hero.setPreferredSize(new Dimension(heroWidth, 260)); // preferred height approx

        // Title
        JLabel title = new JLabel("Where History Meets Romance", SwingConstants.CENTER);
        title.setFont(new Font("Georgia", Font.BOLD, 60));
        title.setForeground(new Color(245, 235, 225));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtitle — use centered HTML
        JLabel subtitle = new JLabel(
                "<html><div style='text-align:center;'>"
                        + "Discover rare treasures and timeless tales.<br>"
                        + "Each book tells a story beyond its pages."
                        + "</div></html>", SwingConstants.CENTER);
        subtitle.setFont(new Font("Serif", Font.PLAIN, 20));
        subtitle.setForeground(new Color(230, 215, 200));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setBorder(BorderFactory.createEmptyBorder(18, 0, 18, 0));

        // Explore button
        JButton explore = new JButton("Explore books →");
        explore.setFont(new Font("Serif", Font.BOLD, 20));
        explore.setBackground(new Color(126, 80, 75));
        explore.setForeground(new Color(255, 240, 230));
        explore.setFocusPainted(false);
        explore.setPreferredSize(new Dimension(260, 50));
        explore.setMaximumSize(new Dimension(260, 50));
        explore.setAlignmentX(Component.CENTER_ALIGNMENT);

        explore.addActionListener(e -> {
            new BookWindow().setVisible(true);
            dispose();
        });

        // Add spacing and components to hero
        hero.add(Box.createVerticalStrut(6));
        hero.add(title);
        hero.add(Box.createVerticalStrut(8));
        hero.add(subtitle);
        hero.add(Box.createVerticalStrut(12));
        hero.add(explore);
        hero.add(Box.createVerticalStrut(6));

        // Put hero in outerFlow (FlowLayout will center the panel)
        outerFlow.add(hero);

        // Wrap in vertical box to give top/bottom margins
        JPanel verticalWrap = new JPanel();
        verticalWrap.setOpaque(false);
        verticalWrap.setLayout(new BoxLayout(verticalWrap, BoxLayout.Y_AXIS));
        verticalWrap.add(Box.createVerticalStrut(20));
        verticalWrap.add(outerFlow);
        verticalWrap.add(Box.createVerticalStrut(20));

        return verticalWrap;
    }

    // --------------------------------------------------
    // FEATURED SECTION
    // --------------------------------------------------
    private JPanel createFeatured() {
        JPanel wrapper = new JPanel();
        wrapper.setOpaque(false);
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);

        JLabel title = new JLabel("Featured Treasures");
        title.setFont(new Font("Georgia", Font.BOLD, 28));
        title.setForeground(new Color(245, 230, 210));
        top.add(title, BorderLayout.WEST);

        JLabel viewAll = new JLabel("<html><u>View All →</u></html>");
        viewAll.setForeground(new Color(225, 200, 175));
        viewAll.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        viewAll.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                new BookWindow().setVisible(true);
                dispose();
            }
        });

        top.add(viewAll, BorderLayout.EAST);

        wrapper.add(top);
        wrapper.add(Box.createVerticalStrut(12));

        JLabel small = new JLabel("Carefully curated selections from our collection");
        small.setFont(new Font("Serif", Font.PLAIN, 14));
        small.setForeground(new Color(215, 190, 170));
        small.setBorder(BorderFactory.createEmptyBorder(6, 0, 18, 0));
        wrapper.add(small);

        JPanel grid = new JPanel(new GridLayout(1, 4, 28, 0));
        grid.setOpaque(false);
        grid.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        BookService bookService = new BookService();
        cartService = new CartService();
     // ⭐ CUSTOM 4 BOOKS ONLY FOR HOMEPAGE ⭐
        List<Book> featured = new ArrayList<>();
        featured.add(BookData.getFantasyBooks().get(0));
        featured.add(BookData.getFantasyBooks().get(1));
        featured.add(BookData.getFantasyBooks().get(2));
        featured.add(BookData.getFantasyBooks().get(3));

        for (Book b : featured) {
            grid.add(new BookCardPanel(b, cartService));
        }


        wrapper.add(grid);

        JLabel footerMsg = new JLabel(
                "<html><div style='text-align:center;'>"
                        + "Begin Your Journey Through Time<br>"
                        + "Join our community of collectors and literature enthusiasts."
                        + "</div></html>",
                SwingConstants.CENTER);
        footerMsg.setFont(new Font("Serif", Font.ITALIC, 15));
        footerMsg.setForeground(new Color(230, 210, 190));
        footerMsg.setBorder(BorderFactory.createEmptyBorder(26, 0, 0, 0));
        wrapper.add(footerMsg);

        return wrapper;
    }

    // main method so you can run HomeWindow directly
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomeWindow().setVisible(true));
    }
}
