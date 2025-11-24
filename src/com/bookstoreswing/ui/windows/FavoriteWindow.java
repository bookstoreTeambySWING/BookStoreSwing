package com.bookstoreswing.ui.windows;

import com.bookstoreswing.service.FavoriteService;
import com.bookstoreswing.app.MainApp;
import com.bookstoreswing.ui.components.HeaderPanel;
import com.bookstoreswing.ui.components.FooterPanel;
import com.bookstoreswing.ui.panels.FavoritePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class FavoriteWindow extends JFrame {

    private final FavoriteService favService;
    private Image backgroundImage;

    public FavoriteWindow(FavoriteService favService) {
        this.favService = favService;

        setTitle("Your Favorites");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 720);
        setLocationRelativeTo(null);

        // Load background from the correct path
        loadBackground();
        
        setupUI();
    }

    private void loadBackground() {
        try {
            // CORRECT PATH - use resources/assets/background/bg.jpg
            String imagePath = "resources/assets/background/bg.jpg";
            File imageFile = new File(imagePath);
            
            if (imageFile.exists()) {
                System.out.println("✓ Loading background from: " + imageFile.getAbsolutePath());
                ImageIcon imageIcon = new ImageIcon(imagePath);
                backgroundImage = imageIcon.getImage();
                backgroundImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            } else {
                System.out.println("✗ Background not found at: " + imageFile.getAbsolutePath());
                System.out.println("⚠ Using fallback gradient background");
                backgroundImage = createFallbackBackground();
            }
        } catch (Exception e) {
            System.err.println("Error loading background: " + e.getMessage());
            backgroundImage = createFallbackBackground();
        }
    }

    private Image createFallbackBackground() {
        // Create a simple solid color background without BufferedImage
        int width = getWidth();
        int height = getHeight();
        
        // Create an image using basic Image
        Image image = createImage(width, height);
        Graphics g = image.getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        
        // Create elegant antique bookshop gradient
        GradientPaint gradient = new GradientPaint(
            0, 0, new Color(45, 35, 30), 
            width, height, new Color(25, 20, 15)
        );
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);
        
        g2d.dispose();
        return image;
    }

    private void setupUI() {
        // Create main panel with background
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                // Draw background image
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
                
                // Add dark overlay for better readability
                g.setColor(new Color(15, 10, 5, 150));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setOpaque(true);
        setContentPane(mainPanel);

        // Header
        HeaderPanel header = new HeaderPanel("Antiquarian");
        header.setActivePage("Favorite");
        setupHeaderNavigation(header);
        mainPanel.add(header, BorderLayout.NORTH);

        // Favorite panel
        setupFavoriteContent(mainPanel);

        // Footer
        mainPanel.add(new FooterPanel(), BorderLayout.SOUTH);
    }

    private void setupFavoriteContent(JPanel mainPanel) {
        JPanel contentContainer = new JPanel(new BorderLayout());
        contentContainer.setOpaque(false);
        contentContainer.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        
        FavoritePanel favoritePanel = new FavoritePanel(favService);
        JScrollPane scrollPane = createStyledScrollPane(favoritePanel);
        contentContainer.add(scrollPane, BorderLayout.CENTER);
        
        mainPanel.add(contentContainer, BorderLayout.CENTER);
    }

    private JScrollPane createStyledScrollPane(FavoritePanel panel) {
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        // Custom scroll bar
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(16);
        verticalScrollBar.setPreferredSize(new Dimension(10, Integer.MAX_VALUE));
        
        // Modern scroll bar design
        verticalScrollBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(160, 120, 80);
                this.trackColor = new Color(50, 40, 35, 100);
            }
            
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createInvisibleButton();
            }
            
            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createInvisibleButton();
            }
            
            private JButton createInvisibleButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });
        
        return scrollPane;
    }

    private void setupHeaderNavigation(HeaderPanel header) {
        header.addHomeListener(e -> navigateToHome());
        header.addBooksListener(e -> navigateToBooks());
        header.addFavoriteListener(e -> {}); // Current page
        header.addCartListener(e -> navigateToCart());
    }

    private void navigateToHome() {
        dispose();
        SwingUtilities.invokeLater(() -> new HomeWindow().setVisible(true));
    }

    private void navigateToBooks() {
        dispose();
        SwingUtilities.invokeLater(() -> new BookWindow().setVisible(true));
    }

    private void navigateToCart() {
        dispose();
        SwingUtilities.invokeLater(() -> new CartPage(MainApp.CART).setVisible(true));
    }
}