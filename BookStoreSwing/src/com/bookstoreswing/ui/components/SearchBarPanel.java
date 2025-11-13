package com.bookstoreswing.ui.components;

import javax.swing.*;
import java.awt.*;

/**
 * Placeholder search bar component
 */
public class SearchBarPanel extends JPanel {
    private JTextField tf = new JTextField(20);
    public SearchBarPanel(){
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(new JLabel("Search:"));
        add(tf);
    }
}
