package com.bookstoreswing.ui.windows;

import com.bookstoreswing.ui.components.FooterPanel;
import com.bookstoreswing.ui.components.HeaderPanel;
import com.bookstoreswing.data.BookData;
import com.bookstoreswing.model.Book;
import com.bookstoreswing.service.CartService;
import com.bookstoreswing.ui.components.BookCardPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
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
        header.setActivePage("Home");
        root.add(header, BorderLayout.NORTH);

        // Navigation listeners
        header.addHomeListener(e -> {}); // Already home

        header.addBooksListener(e -> {
            dispose();
            new BookWindow().setVisible(true);
        });

        header.addFavoriteListener(e -> {
            JOptionPane.showMessageDialog(this, "Favorite page not implemented yet.");
        });

        header.addCartListener(e -> {
            JOptionPane.showMessageDialog(this, "Cart page not implemented yet.");
        });
        if (bg == null) {
            System.out.println("Background image not found — using fallback.");
        }

        // Main content
        JPanel main = new JPanel();
        main.setOpaque(false);
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        main.add(Box.createVerticalStrut(70));
        main.add(createHeroSection());
        main.add(Box.createVerticalStrut(65));
        main.add(createFeatured());
        main.add(Box.createVerticalStrut(50));
        main.add(new FooterPanel());
        main.add(Box.createVerticalStrut(40));

        JScrollPane scroll = new JScrollPane(main);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);
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

        return null;
    }

    private JPanel createHeroSection() {
        JPanel outerFlow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        outerFlow.setOpaque(false);

        JPanel hero = new JPanel();
        hero.setOpaque(false);
        hero.setLayout(new BoxLayout(hero, BoxLayout.Y_AXIS));
        hero.setMaximumSize(new Dimension(820, Integer.MAX_VALUE));

        JLabel title = new JLabel("Where History Meets Romance");
        title.setFont(new Font("Georgia", Font.BOLD, 60));
        title.setForeground(new Color(245, 235, 225));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel(
                "<html><div style='text-align:center;'>Discover rare treasures and timeless tales.<br>Each book tells a story beyond its pages.</div></html>");
        subtitle.setFont(new Font("Serif", Font.PLAIN, 20));
        subtitle.setForeground(new Color(230, 215, 200));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton explore = new JButton("Explore books →");
        explore.setFont(new Font("Serif", Font.BOLD, 20));
        explore.setBackground(new Color(126, 80, 75));
        explore.setForeground(new Color(255, 240, 230));
        explore.setFocusPainted(false);
        explore.setAlignmentX(Component.CENTER_ALIGNMENT);

        explore.addActionListener(e -> {
            dispose();
            new BookWindow().setVisible(true);
        });

        hero.add(title);
        hero.add(Box.createVerticalStrut(12));
        hero.add(subtitle);
        hero.add(Box.createVerticalStrut(12));
        hero.add(explore);

        outerFlow.add(hero);
        return outerFlow;
    }

    private JPanel createFeatured() {

        JPanel wrapper = new JPanel();
        wrapper.setOpaque(false);
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));

        JLabel title = new JLabel("Featured Treasures");
        title.setFont(new Font("Georgia", Font.BOLD, 28));
        title.setForeground(new Color(245, 230, 210));
        wrapper.add(title);

        wrapper.add(Box.createVerticalStrut(10));

        JLabel small = new JLabel("Carefully curated selections from our collection");
        small.setFont(new Font("Serif", Font.PLAIN, 14));
        small.setForeground(new Color(215, 190, 170));
        wrapper.add(small);

        wrapper.add(Box.createVerticalStrut(20));

        JPanel grid = new JPanel(new GridLayout(1, 4, 25, 0));
        grid.setOpaque(false);

        cartService = new CartService();

        List<Book> featured = new ArrayList<>();
        featured.add(BookData.getFantasyBooks().get(0));
        featured.add(BookData.getFantasyBooks().get(1));
        featured.add(BookData.getFantasyBooks().get(2));
        featured.add(BookData.getFantasyBooks().get(3));

        for (Book b : featured) {
            grid.add(new BookCardPanel(b, cartService));
        }

        wrapper.add(grid);

        return wrapper;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomeWindow().setVisible(true));
    }
}
