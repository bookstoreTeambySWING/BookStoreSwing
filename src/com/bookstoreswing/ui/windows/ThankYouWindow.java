package com.bookstoreswing.ui.windows;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import com.bookstoreswing.ui.components.HeaderPanel;
import com.bookstoreswing.utils.ImageLoader;
import com.bookstoreswing.ui.windows.FavoriteWindow;
import com.bookstoreswing.app.MainApp;
public class ThankYouWindow extends JFrame {

    private Image bgImage;

    public ThankYouWindow(double totalAmount) {

        // Load background (same as every page)
        bgImage = ImageLoader.loadImage("assets/background/bg.jpg");

        setTitle("Order Confirmation");
        setSize(1100, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== ROOT WITH BACKGROUND =====
        JPanel root = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgImage != null)
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        setContentPane(root);

        // ====== NAVBAR AT TOP ======
        HeaderPanel header = new HeaderPanel("Antiquarian");
        header.setActivePage("");
        root.add(header, BorderLayout.NORTH);

        header.addHomeListener(e -> { new HomeWindow().setVisible(true); dispose(); });
        header.addBooksListener(e -> { new BookWindow().setVisible(true); dispose(); });
        header.addFavoriteListener(e -> {
            dispose();
            new FavoriteWindow(MainApp.FAVORITES).setVisible(true);
        });

        header.addCartListener(e -> { new CartPage(MainApp.CART).setVisible(true); dispose(); });

        // ===== CENTER CONFIRMATION BOX =====
        JPanel box = new JPanel();
        box.setOpaque(false);
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setBorder(new LineBorder(new Color(200, 181, 122), 3, true));
        box.setPreferredSize(new Dimension(650, 380));
        box.setMaximumSize(new Dimension(650, 380));

        // ✔ Icon
        JLabel check = new JLabel("\u2714", SwingConstants.CENTER);
        check.setFont(new Font("Serif", Font.BOLD, 55));
        check.setForeground(new Color(200, 181, 122));
        check.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(Box.createVerticalStrut(20));
        box.add(check);

        // Title
        JLabel title = new JLabel("Thank you for your order");
        title.setFont(new Font("Georgia", Font.BOLD, 30));
        title.setForeground(new Color(245, 230, 210));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(Box.createVerticalStrut(15));
        box.add(title);

        // Subtitle
        JLabel desc = new JLabel(
            "<html><center>Your order has been successfully received.<br>" +
            "We are preparing your valuable items with the utmost care.</center></html>"
        );
        desc.setFont(new Font("Georgia", Font.PLAIN, 16));
        desc.setForeground(new Color(235, 220, 200));
        desc.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(Box.createVerticalStrut(15));
        box.add(desc);

        // ===== ORDER DETAILS BOX =====
        JPanel infoBox = new JPanel(new GridLayout(2, 2));
        infoBox.setOpaque(false);
        infoBox.setBorder(new LineBorder(new Color(200, 181, 122), 2));

        // labels
        JLabel lbl1 = new JLabel("total amount");
        JLabel lbl2 = new JLabel("payment method");
        lbl1.setFont(new Font("Georgia", Font.BOLD, 16));
        lbl2.setFont(new Font("Georgia", Font.BOLD, 16));
        lbl1.setForeground(new Color(245, 230, 210));
        lbl2.setForeground(new Color(245, 230, 210));

        // values
        JLabel val1 = new JLabel(String.format("%.2f €", totalAmount).replace('.', ','));
        JLabel val2 = new JLabel("bank transfer");
        val1.setHorizontalAlignment(SwingConstants.RIGHT);
        val2.setHorizontalAlignment(SwingConstants.RIGHT);
        val1.setFont(new Font("Georgia", Font.PLAIN, 16));
        val2.setFont(new Font("Georgia", Font.PLAIN, 16));
        val1.setForeground(new Color(245, 230, 210));
        val2.setForeground(new Color(245, 230, 210));

        infoBox.add(lbl1);
        infoBox.add(val1);
        infoBox.add(lbl2);
        infoBox.add(val2);

        box.add(Box.createVerticalStrut(20));
        box.add(infoBox);
        box.add(Box.createVerticalStrut(20));

        // ===== FINAL MESSAGE =====
        JLabel finalMsg = new JLabel(
            "<html><center>You will receive a confirmation email shortly.<br>" +
            "Old books deserve to be cherished…</center></html>"
        );
        finalMsg.setFont(new Font("Georgia", Font.PLAIN, 14));
        finalMsg.setForeground(new Color(245, 230, 210));
        finalMsg.setAlignmentX(Component.CENTER_ALIGNMENT);
        box.add(finalMsg);

        box.add(Box.createVerticalStrut(25));

        // ===== BACK TO HOME BUTTON =====
        JButton back = new JButton("Back to Home");
        back.setFont(new Font("Georgia", Font.BOLD, 18));
        back.setBackground(new Color(200, 181, 122));
        back.setForeground(new Color(35, 30, 30));
        back.setFocusPainted(false);
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.setBorder(BorderFactory.createEmptyBorder(8, 25, 8, 25));

        back.addActionListener(e -> {
            new HomeWindow().setVisible(true);
            dispose();
        });

        box.add(back);

        // ===== CENTER THE BOX =====
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.add(box);

        root.add(centerPanel, BorderLayout.CENTER);
    }
}
