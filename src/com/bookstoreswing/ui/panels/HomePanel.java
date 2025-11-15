package com.bookstoreswing.ui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class HomePanel extends JPanel {
    private Image backgroundImage;

    public HomePanel(Runnable onNavigate) {
        // ✅ Load from /assets/ folder inside src
        backgroundImage = new ImageIcon(getClass().getResource("/assets/bg.jpg")).getImage();
        System.out.println("Image loaded? " + (backgroundImage != null));


        setLayout(new BorderLayout());
        setOpaque(false); // So background shows behind components

        // Title label
        JLabel title = new JLabel("Welcome to Antiquarian", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 36));
        title.setForeground(new Color(166, 97, 90)); // Theme color #A6615A
        add(title, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // 🖼️ Stretch image to fill panel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            // 🎨 Fallback background color if image not found
            g.setColor(new Color(250, 240, 230)); // light beige tone
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}
