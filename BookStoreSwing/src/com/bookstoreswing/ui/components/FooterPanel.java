package com.bookstoreswing.ui.components;

import javax.swing.*;
import java.awt.*;

/**
 * Simple footer
 */
public class FooterPanel extends JPanel {
    public FooterPanel(){
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(new JLabel("© 2025 BookStoreSwing"));
    }
}
