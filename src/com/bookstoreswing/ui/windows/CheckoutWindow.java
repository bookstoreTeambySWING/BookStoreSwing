package com.bookstoreswing.ui.windows;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import com.bookstoreswing.model.User;
import com.bookstoreswing.service.CartService;
import com.bookstoreswing.ui.controllers.CheckoutController;
import com.bookstoreswing.utils.Validator;

public class CheckoutWindow extends JFrame {

    private JTextField nameField;
    private JTextField contactField;
    private JTextArea addressArea;

    private JLabel nameError;
    private JLabel contactError;
    private JLabel addressError;

    private CartService cartService;

    // ---- FIXED: no-argument constructor so CartPage can call it ----
    public CheckoutWindow(CartService cs) {
        this.cartService = cs;
        buildUI();
    }

    private void buildUI() {
        setTitle("Personal Information");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBackground(new Color(35, 30, 30));
        root.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        setContentPane(root);

        JLabel title = new JLabel("Personal information");
        title.setFont(new Font("Georgia", Font.BOLD, 28));
        title.setForeground(new Color(245, 230, 210));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        root.add(title);
        root.add(Box.createVerticalStrut(20));

        // ----------- Name -----------
        JLabel nameLabel = new JLabel("Name:");
        styleLabel(nameLabel);

        nameField = new JTextField();
        styleField(nameField);

        nameError = errorLabel();

        // ----------- Contact -----------
        JLabel contactLabel = new JLabel("Email or Phone:");
        styleLabel(contactLabel);

        contactField = new JTextField();
        styleField(contactField);

        contactError = errorLabel();

        // ----------- Address -----------
        JLabel addressLabel = new JLabel("Address:");
        styleLabel(addressLabel);

        addressArea = new JTextArea(3, 20);
        addressArea.setLineWrap(true);
        addressArea.setWrapStyleWord(true);
        styleTextArea(addressArea);

        addressError = errorLabel();

        // ---- Add components to root ----
        root.add(nameLabel);     root.add(nameField);     root.add(nameError);
        root.add(Box.createVerticalStrut(10));

        root.add(contactLabel);  root.add(contactField);  root.add(contactError);
        root.add(Box.createVerticalStrut(10));

        root.add(addressLabel);
        JScrollPane sp = new JScrollPane(addressArea);
        sp.setBorder(BorderFactory.createLineBorder(new Color(200, 181, 122), 2));
        root.add(sp);
        root.add(addressError);

        root.add(Box.createVerticalStrut(20));

        // ---- DONE button ----
        JButton done = new JButton("Done");
        done.setFont(new Font("Georgia", Font.BOLD, 18));
        done.setBackground(new Color(200, 181, 122));
        done.setForeground(new Color(35, 30, 30));
        done.setFocusPainted(false);
        done.setAlignmentX(Component.CENTER_ALIGNMENT);

        done.addActionListener(e -> validateAndSubmit());

        root.add(done);
        root.add(Box.createVerticalStrut(20));
    }

    private void validateAndSubmit() {
        boolean ok = true;

        String name = nameField.getText().trim();
        String contact = contactField.getText().trim();
        String address = addressArea.getText().trim();

        // ---- Validation rules ----
        if (name.isEmpty() || name.length() < 2) {
            nameError.setText("Invalid name");
            ok = false;
        } else nameError.setText("");

        if (!Validator.isValidContact(contact)) {
            contactError.setText("Invalid email or phone");
            ok = false;
        } else contactError.setText("");

        if (address.length() < 5) {
            addressError.setText("Address too short");
            ok = false;
        } else addressError.setText("");

        // ---- If invalid, stop ----
        if (!ok) return;

        // ---- Create User ----
        User u = new User("guest", name, address);

        // ---- Process order ----
        boolean success = new CheckoutController().processOrder(u, cartService.getItems());

        if (success) {

            // Calculate total from the cart (in €)
        	double subtotal = cartService.getTotal() / 100.0;
        	double taxes = subtotal * 0.08;
        	double finalTotal = subtotal + taxes;

        	// Show the confirmation first
        	new ThankYouWindow(finalTotal).setVisible(true);

        	// THEN clear the cart (after using it)
        	cartService.clear();

        	dispose();

        }


    }

    // ---------- Helpers ----------

    private JLabel errorLabel() {
        JLabel lbl = new JLabel(" ");
        lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lbl.setForeground(Color.RED);
        return lbl;
    }

    private void styleLabel(JLabel lbl) {
        lbl.setFont(new Font("Georgia", Font.BOLD, 16));
        lbl.setForeground(new Color(240, 220, 190));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private void styleField(JTextField f) {
        f.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        f.setBorder(new LineBorder(new Color(200, 181, 122), 2));
        f.setBackground(new Color(60, 50, 45));
        f.setForeground(new Color(245, 230, 210));
        f.setCaretColor(Color.WHITE);
    }

    private void styleTextArea(JTextArea a) {
        a.setBorder(new LineBorder(new Color(200, 181, 122), 2));
        a.setBackground(new Color(60, 50, 45));
        a.setForeground(new Color(245, 230, 210));
        a.setCaretColor(Color.WHITE);
    }
}
