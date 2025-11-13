package com.bookstoreswing.ui.windows;

import javax.swing.*;
import java.awt.*;
import com.bookstoreswing.ui.components.HeaderPanel;

public class BookWindow extends JFrame {

    public BookWindow() {
        setTitle("Antiquarian - Books");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 800);
        setLocationRelativeTo(null);

        // Load background image
        Image backgroundImage = null;
        try {
            java.net.URL u = getClass().getResource("/assets/bg.jpg");
            if (u == null) u = getClass().getResource("/assets/bg.jpg.jpg");
            if (u != null) backgroundImage = new ImageIcon(u).getImage();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        final Image bgFinal = backgroundImage;

        // Background panel
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgFinal != null)
                    g.drawImage(bgFinal, 0, 0, getWidth(), getHeight(), this);
            }
        };
        setContentPane(backgroundPanel);

        // Add the navbar
        HeaderPanel header = new HeaderPanel("Antiquarian");
        backgroundPanel.add(header, BorderLayout.NORTH);

        // Center content
        JLabel label = new JLabel("📚 Explore Our Collection", SwingConstants.CENTER);
        label.setFont(new Font("Georgia", Font.BOLD, 30));
        label.setForeground(new Color(255, 245, 230));
        backgroundPanel.add(label, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookWindow().setVisible(true));
    }
}
