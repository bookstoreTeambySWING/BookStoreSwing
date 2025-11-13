package com.bookstoreswing.ui.windows;

import com.bookstoreswing.ui.components.BookCardPanel;
import com.bookstoreswing.ui.components.HeaderPanel;
import com.bookstoreswing.model.Book;
import com.bookstoreswing.service.BookService;
import com.bookstoreswing.service.CartService;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

/**
 * Antiquarian Home Page — visually matching the screenshots
 */
public class HomeWindow extends JFrame {

    private CartService cartService;

    public HomeWindow() {
        setTitle("Antiquarian - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 800);
        setLocationRelativeTo(null);

        // ✅ Load background safely
        Image backgroundImage = loadBackgroundImage();

        final Image bgFinal = backgroundImage;

        // Root panel paints background + overlay
        JPanel root = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgFinal != null) {
                    g.drawImage(bgFinal, 0, 0, getWidth(), getHeight(), this);
                    g.setColor(new Color(20, 10, 10, 160)); // semi-dark overlay
                    g.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    g.setColor(new Color(45, 35, 35)); // fallback background
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };
        setContentPane(root);

        // --- Navbar (top header) ---
        HeaderPanel header = new HeaderPanel("Antiquarian");
        root.add(header, BorderLayout.NORTH);

        // --- Center section with scroll ---
        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setOpaque(false);

        JPanel vertical = new JPanel();
        vertical.setOpaque(false);
        vertical.setLayout(new BoxLayout(vertical, BoxLayout.Y_AXIS));

        // === HERO SECTION ===
        JPanel hero = createHeroSection();
        vertical.add(hero);
        vertical.add(Box.createVerticalStrut(50));

        // === FEATURED SECTION (scrolls to) ===
        JPanel featured = createFeaturedSection();
        vertical.add(featured);

        // === Scrollable setup ===
        JScrollPane scroll = new JScrollPane(vertical);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        centerWrapper.add(scroll, BorderLayout.CENTER);

        root.add(centerWrapper, BorderLayout.CENTER);
    }

    // ---- Load background image (tries multiple extensions) ----
    private Image loadBackgroundImage() {
        String[] paths = {"/assets/bg.jpg", "/assets/bg.jpeg", "/assets/bg.png"};
        for (String p : paths) {
            try {
                URL u = getClass().getResource(p);
                if (u != null) return new ImageIcon(u).getImage();
            } catch (Exception ignored) {}
        }
        try {
            return Toolkit.getDefaultToolkit().getImage("assets/bg.jpg.jpg");
        } catch (Exception ignored) {}
        return null;
    }

    // ---- HERO SECTION ----
    private JPanel createHeroSection() {
        JPanel hero = new JPanel(new GridBagLayout());
        hero.setOpaque(false);
        hero.setPreferredSize(new Dimension(0, 500));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Title
        JLabel title = new JLabel("Where History Meets Romance");
        title.setFont(new Font("Georgia", Font.BOLD, 46));
        title.setForeground(new Color(245, 230, 210));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        hero.add(title, gbc);

        // Subtitle
        gbc.gridy++;
        gbc.insets = new Insets(20, 0, 10, 0);
        JLabel subtitle = new JLabel("<html><div style='text-align:center;'>Discover rare treasures and timeless tales from centuries past.<br>Each book tells a story beyond its pages.</div></html>");
        subtitle.setFont(new Font("Serif", Font.PLAIN, 20));
        subtitle.setForeground(new Color(235, 220, 190));
        hero.add(subtitle, gbc);

        // Button
        gbc.gridy++;
        gbc.insets = new Insets(40, 0, 0, 0);
        JButton exploreBtn = new JButton("Explore Books →");
        exploreBtn.setFont(new Font("Serif", Font.BOLD, 18));
        exploreBtn.setBackground(new Color(150, 110, 90));
        exploreBtn.setForeground(new Color(245, 230, 210));
        exploreBtn.setFocusPainted(false);
        exploreBtn.setPreferredSize(new Dimension(200, 48));

        exploreBtn.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> new BookWindow().setVisible(true));
        });

        hero.add(exploreBtn, gbc);

        return hero;
    }

    // ---- FEATURED SECTION ----
    private JPanel createFeaturedSection() {
        JPanel wrapper = new JPanel();
        wrapper.setOpaque(false);
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(0, 40, 15, 40));

        JLabel sectionTitle = new JLabel("Featured Treasures");
        sectionTitle.setFont(new Font("Georgia", Font.BOLD, 26));
        sectionTitle.setForeground(new Color(235, 220, 190));
        header.add(sectionTitle, BorderLayout.WEST);

        JLabel viewAll = new JLabel("<html><u>View All →</u></html>");
        viewAll.setForeground(new Color(230, 200, 170));
        header.add(viewAll, BorderLayout.EAST);

        wrapper.add(header);

        // Books Grid
        JPanel gridWrap = new JPanel(new GridLayout(0, 4, 20, 20));
        gridWrap.setOpaque(false);
        gridWrap.setBorder(BorderFactory.createEmptyBorder(10, 40, 40, 40));

        BookService bookService = new BookService();
        cartService = new CartService();
        List<Book> books = bookService.getAllBooks();

        int count = 0;
        for (Book b : books) {
            if (count >= 4) break;
            gridWrap.add(new BookCardPanel(b, cartService));
            count++;
        }

        wrapper.add(gridWrap);

        // Footer message under featured books
        JLabel footer = new JLabel(
                "<html><div style='text-align:center;'>Join our community of collectors and literature enthusiasts.<br>Discover stories that have captivated hearts for centuries.</div></html>",
                SwingConstants.CENTER);
        footer.setFont(new Font("Serif", Font.ITALIC, 15));
        footer.setForeground(new Color(230, 210, 180));
        footer.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        wrapper.add(footer);

        return wrapper;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomeWindow().setVisible(true));
    }
}
