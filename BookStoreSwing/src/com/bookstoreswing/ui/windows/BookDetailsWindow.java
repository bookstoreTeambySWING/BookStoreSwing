package com.bookstoreswing.ui.windows;

import javax.swing.*;
import java.awt.*;
import com.bookstoreswing.model.Book;

/**
 * Detailed book window (optional)
 */
public class BookDetailsWindow extends JFrame {
    public BookDetailsWindow(Book b){
        setTitle(b.getTitle());
        setSize(400,300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(new JLabel("<html><h2>"+b.getTitle()+"</h2><i>"+b.getAuthor()+"</i></html>"), BorderLayout.NORTH);
        add(new JLabel("Price: " + b.getPrice() + " DA"), BorderLayout.CENTER);
        setVisible(true);
    }
}
