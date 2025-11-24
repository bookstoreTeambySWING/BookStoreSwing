package com.bookstoreswing.ui.windows;

import com.bookstoreswing.ui.components.FooterPanel;
import com.bookstoreswing.ui.components.HeaderPanel;
import com.bookstoreswing.data.BookData;
import com.bookstoreswing.model.Book;
import com.bookstoreswing.app.MainApp;
import com.bookstoreswing.service.CartService;
import com.bookstoreswing.ui.components.BookCardPanel;
import com.bookstoreswing.utils.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HomeWindow extends JFrame {

    private CartService cartService;
    
    // Consistent color scheme
    private final Color TITLE_COLOR = new Color(245, 230, 210);
    private final Color SUBTITLE_COLOR = new Color(230, 210, 180);
    private final Color TEXT_COLOR = new Color(240, 220, 190);
    private final Color BUTTON_COLOR = new Color(130, 85, 75);
    private final Color OVERLAY_COLOR = new Color(20, 10, 10, 140);

    public HomeWindow() {
        setTitle("Antiquarian - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 860);
        setLocationRelativeTo(null);

        // Load background
        Image bg = ImageLoader.loadBackgroundImage(getWidth(), getHeight());

        // Background panel
        JPanel root = new JPanel(new BorderLayout()) {
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
        root.setOpaque(false);
        setContentPane(root);

        // Header
        HeaderPanel header = new HeaderPanel("Antiquarian");
        header.setActivePage("Home");

        header.addHomeListener(e -> {});
        header.addBooksListener(e -> {
            dispose();
            new BookWindow().setVisible(true);
        });
        header.addFavoriteListener(e -> {
            dispose();
            new FavoriteWindow(MainApp.FAVORITES).setVisible(true);
        });
        header.addCartListener(e -> {
            dispose();
            new CartPage(MainApp.CART).setVisible(true);
        });

        root.add(header, BorderLayout.NORTH);

        // Main content
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setOpaque(false);

        main.add(Box.createVerticalStrut(70));
        main.add(createHeroSection());
        main.add(Box.createVerticalStrut(65));
        main.add(createFeaturedSection());
        main.add(Box.createVerticalStrut(50));
        main.add(new FooterPanel());
        main.add(Box.createVerticalStrut(35));

        JScrollPane scroll = new JScrollPane(main);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(18);

        root.add(scroll, BorderLayout.CENTER);
    }

    private JPanel createHeroSection() {
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapper.setOpaque(false);

        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setOpaque(false);

        JLabel title = new JLabel("Where History Meets Romance");
        title.setFont(new Font("Georgia", Font.BOLD, 58));
        title.setForeground(TITLE_COLOR);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel(
                "<html><div style='text-align:center;'>Discover rare treasures and timeless tales.<br>Each book tells a story beyond its pages.</div></html>");
        subtitle.setFont(new Font("Serif", Font.PLAIN, 20));
        subtitle.setForeground(SUBTITLE_COLOR);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton explore = new JButton("Explore books →");
        explore.setFont(new Font("Serif", Font.BOLD, 20));
        explore.setBackground(BUTTON_COLOR);
        explore.setForeground(Color.WHITE);
        explore.setFocusPainted(false);
        explore.setAlignmentX(Component.CENTER_ALIGNMENT);

        explore.addActionListener(e -> {
            dispose();
            new BookWindow().setVisible(true);
        });

        box.add(title);
        box.add(Box.createVerticalStrut(12));
        box.add(subtitle);
        box.add(Box.createVerticalStrut(12));
        box.add(explore);

        wrapper.add(box);
        return wrapper;
    }

    private JPanel createFeaturedSection() {
        JPanel wrapper = new JPanel();
        wrapper.setOpaque(false);
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));

        JPanel bgBox = new JPanel();
        bgBox.setLayout(new BoxLayout(bgBox, BoxLayout.Y_AXIS));
        bgBox.setOpaque(true);
        bgBox.setBackground(new Color(65, 50, 40, 200));
        bgBox.setBorder(BorderFactory.createEmptyBorder(35, 35, 35, 35));

        JLabel title = new JLabel("Featured Treasures");
        title.setFont(new Font("Georgia", Font.BOLD, 30));
        title.setForeground(TITLE_COLOR);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        bgBox.add(title);

        bgBox.add(Box.createVerticalStrut(8));

        JLabel subtitle = new JLabel("Carefully curated selections from our collection");
        subtitle.setFont(new Font("Serif", Font.PLAIN, 17));
        subtitle.setForeground(SUBTITLE_COLOR);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        bgBox.add(subtitle);

        bgBox.add(Box.createVerticalStrut(25));

        JPanel grid = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 15));
        grid.setOpaque(false);

        cartService = MainApp.CART;
        List<Book> all = BookData.getAllBooks();
        int count = Math.min(4, all.size());

        for (int i = 0; i < count; i++) {
            Book b = all.get(i);
            BookCardPanel card = new BookCardPanel(b, cartService);
            card.setPreferredSize(new Dimension(210, 380));
            grid.add(card);
        }

        bgBox.add(grid);
        wrapper.add(bgBox);
        return wrapper;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomeWindow().setVisible(true));
    }
}