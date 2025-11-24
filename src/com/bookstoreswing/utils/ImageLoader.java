package com.bookstoreswing.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.io.File;

public class ImageLoader {
    
    // UPDATED METHOD: Fixed image loading with path correction
    public static ImageIcon loadIcon(String path, int width, int height) {
        try {
            // First try classpath
            URL resource = ImageLoader.class.getClassLoader().getResource(path);
            if (resource != null) {
                ImageIcon originalIcon = new ImageIcon(resource);
                Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImage);
            } else {
                // Try filesystem with correct path
                String correctedPath = correctImagePath(path);
                File file = new File(correctedPath);
                if (file.exists()) {
                    ImageIcon originalIcon = new ImageIcon(correctedPath);
                    Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                    return new ImageIcon(scaledImage);
                } else {
                    System.err.println("Image NOT FOUND: " + path);
                    // Don't show error for the screenshot, just return placeholder
                    if (path.contains("Screenshot")) {
                        return createPlaceholderIcon(width, height, "Book Cover");
                    }
                    return createPlaceholderIcon(width, height, "No Image");
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading image: " + path);
            return createPlaceholderIcon(width, height, "Error");
        }
    }
    
    private static String correctImagePath(String path) {
        // Fix common path issues
        if (path.startsWith("/mnt/data/")) {
            // This is the problematic path, try to find it locally
            String filename = path.substring(path.lastIndexOf("/") + 1);
            return "resources/assets/books/" + filename;
        }
        return path;
    }
    
    public static ImageIcon loadIcon(String path) {
        return loadIcon(path, 200, 300); // Default dimensions
    }
    
    public static Image loadImage(String path) {
        return loadImage(path, 200, 300);
    }
    
    public static Image loadImage(String path, int width, int height) {
        ImageIcon icon = loadIcon(path, width, height);
        return icon != null ? icon.getImage() : null;
    }
    
    public static ImageIcon loadImageIcon(String path) {
        return loadIcon(path, 200, 300);
    }
    
    public static ImageIcon loadImageIcon(String path, int width, int height) {
        return loadIcon(path, width, height);
    }
    
    // UPDATED METHOD: Load background image for windows - FIXED PATH
    public static Image loadBackgroundImage(int windowWidth, int windowHeight) {
        // Use the correct path that we know works
        String imagePath = "resources/assets/background/bg.jpg";
        File imageFile = new File(imagePath);
        
        try {
            if (imageFile.exists()) {
                System.out.println("✓ Loading background from: " + imageFile.getAbsolutePath());
                ImageIcon icon = new ImageIcon(imagePath);
                Image image = icon.getImage();
                return image.getScaledInstance(windowWidth, windowHeight, Image.SCALE_SMOOTH);
            } else {
                System.out.println("✗ Background not found at: " + imagePath);
                return createFallbackBackground(windowWidth, windowHeight);
            }
        } catch (Exception e) {
            System.err.println("Error loading background: " + e.getMessage());
            return createFallbackBackground(windowWidth, windowHeight);
        }
    }
    
    // NEW METHOD: Enhanced background image loading with multiple fallbacks
    public static Image loadBackgroundImageWithFallback(int windowWidth, int windowHeight) {
        Image backgroundImage = null;
        
        try {
            // Use the correct path that we know works
            String imagePath = "resources/assets/background/bg.jpg";
            File imageFile = new File(imagePath);
            
            if (imageFile.exists()) {
                System.out.println("✓ Loading background from: " + imageFile.getAbsolutePath());
                ImageIcon imageIcon = new ImageIcon(imagePath);
                backgroundImage = imageIcon.getImage();
                backgroundImage = backgroundImage.getScaledInstance(windowWidth, windowHeight, Image.SCALE_SMOOTH);
            } else {
                System.err.println("✗ Background image not found at: " + imagePath);
                backgroundImage = createFallbackBackground(windowWidth, windowHeight);
            }
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            backgroundImage = createFallbackBackground(windowWidth, windowHeight);
        }
        
        return backgroundImage;
    }
    
    // NEW METHOD: Debug available resources
    public static void debugResourcePaths() {
        System.out.println("=== DEBUG: Checking available resources ===");
        try {
            // Check the correct path first
            String correctPath = "resources/assets/background/bg.jpg";
            File correctFile = new File(correctPath);
            System.out.println("Correct path exists: " + correctFile.exists());
            System.out.println("Correct path: " + correctFile.getAbsolutePath());
            
            // Check if assets directory exists
            URL assetsDir = ImageLoader.class.getClassLoader().getResource("assets");
            if (assetsDir != null) {
                System.out.println("✓ Assets directory found: " + assetsDir);
            } else {
                System.out.println("✗ Assets directory NOT found");
            }
            
            // Check specific background image
            URL bgImage = ImageLoader.class.getClassLoader().getResource("assets/background/bg.jpg");
            if (bgImage != null) {
                System.out.println("✓ Background image found: " + bgImage);
            } else {
                System.out.println("✗ Background image NOT found at: assets/background/bg.jpg");
            }
            
        } catch (Exception e) {
            System.err.println("Error debugging resources: " + e.getMessage());
        }
        System.out.println("=== END DEBUG ===");
    }
    
    // NEW METHOD: Create a beautiful fallback background
    private static Image createFallbackBackground(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        
        // Create an antique bookshop style gradient
        GradientPaint gradient = new GradientPaint(
            0, 0, new Color(70, 50, 40),
            width, height, new Color(40, 30, 25)
        );
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);
        
        // Add subtle texture like old paper
        g2d.setColor(new Color(90, 70, 60, 40));
        for (int i = 0; i < width; i += 60) {
            for (int j = 0; j < height; j += 60) {
                g2d.fillRect(i, j, 1, 1);
            }
        }
        
        g2d.dispose();
        return image;
    }
    
    // NEW METHOD: Create a background panel with the loaded background
    public static JPanel createBackgroundPanel(int windowWidth, int windowHeight) {
        Image bgImage = loadBackgroundImageWithFallback(windowWidth, windowHeight);
        
        return new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                // Draw background image
                if (bgImage != null) {
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                }
                
                // Add dark overlay for better text readability
                g.setColor(new Color(20, 10, 10, 140));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
    }
    
    
    // NEW METHOD: Quick check if background image exists
    public static boolean backgroundImageExists() {
        try {
            // Check the correct path
            File imageFile = new File("resources/assets/background/bg.jpg");
            return imageFile.exists();
        } catch (Exception e) {
            return false;
        }
    }
    
    private static ImageIcon createPlaceholderIcon(int width, int height, String text) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        
        // Set background
        g2d.setColor(new Color(240, 240, 240));
        g2d.fillRect(0, 0, width, height);
        
        // Set border
        g2d.setColor(new Color(200, 200, 200));
        g2d.drawRect(0, 0, width - 1, height - 1);
        
        // Set text
        g2d.setColor(new Color(100, 100, 100));
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Center the text
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int x = (width - textWidth) / 2;
        int y = height / 2;
        
        g2d.drawString(text, x, y);
        g2d.dispose();
        
        return new ImageIcon(image);
    }
}