package com.bookstoreswing.ui.panels;
import javax.swing.*;
import java.awt.*;

public class FavoritePanel extends JPanel {
    public FavoritePanel() {
        setOpaque(false);
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Favorites", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Serif", Font.BOLD, 28));
        add(title, BorderLayout.NORTH);

        JLabel info = new JLabel("<html><div style='text-align:center; color:#f3e8de;'>You haven't added favorites yet.</div></html>", SwingConstants.CENTER);
        info.setFont(new Font("SansSerif", Font.ITALIC, 16));
        add(info, BorderLayout.CENTER);
    }
}
