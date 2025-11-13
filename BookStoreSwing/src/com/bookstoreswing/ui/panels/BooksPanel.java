package com.bookstoreswing.ui.panels;
import javax.swing.*;
import java.awt.*;

public class BooksPanel extends JPanel {

    public BooksPanel() {
        setOpaque(false);
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Books", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Serif", Font.BOLD, 28));
        add(title, BorderLayout.NORTH);

        // Placeholder content: a scrollable grid of book cards (demo)
        JPanel grid = new JPanel(new GridLayout(0, 3, 12, 12));
        grid.setOpaque(false);
        for (int i = 1; i <= 6; i++) {
            JPanel card = makeCard("Book " + i, "Author " + i);
            grid.add(card);
        }
        JScrollPane scroll = new JScrollPane(grid);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        add(scroll, BorderLayout.CENTER);
    }

    private JPanel makeCard(String title, String author) {
        JPanel p = new JPanel();
        p.setBackground(new Color(255, 255, 255, 230));
        p.setLayout(new BorderLayout());
        p.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        JLabel t = new JLabel("<html><b>" + title + "</b><br/><small>" + author + "</small></html>");
        p.add(t, BorderLayout.CENTER);
        return p;
    }
}
