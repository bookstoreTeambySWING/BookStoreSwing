package com.bookstoreswing.ui.windows;

import javax.swing.*;
import java.awt.*;
import com.bookstoreswing.service.CartService;
import com.bookstoreswing.model.CartItem;
import java.util.List;

/**
 * Window showing the cart items and checkout button
 */
public class CartWindow extends JFrame {
    private CartService cartService;
    private DefaultListModel<String> listModel;
    private JList<String> list;
    private JLabel totalLabel;

    public CartWindow(CartService cs){
        this.cartService = cs;
        setTitle("Your Cart");
        setSize(500,400);
        setLocationRelativeTo(null);
        initUI();
        setVisible(true);
    }

    private void initUI(){
        setLayout(new BorderLayout());
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        refreshList();

        add(new JScrollPane(list), BorderLayout.CENTER);
        totalLabel = new JLabel("Total: " + cartService.getTotal() + " DA");
        JButton removeBtn = new JButton("Remove Selected");
        JButton checkoutBtn = new JButton("Checkout");

        removeBtn.addActionListener(e -> {
            int idx = list.getSelectedIndex();
            if(idx >=0){
                cartService.removeAt(idx);
                refreshList();
            }
        });

        checkoutBtn.addActionListener(e -> {
            if(cartService.getItems().isEmpty()){
                JOptionPane.showMessageDialog(this, "Cart is empty");
                return;
            }
            new CheckoutWindow(cartService);
            dispose();
        });

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(removeBtn);
        bottom.add(totalLabel);
        bottom.add(checkoutBtn);
        add(bottom, BorderLayout.SOUTH);
    }

    private void refreshList(){
        listModel.clear();
        List<CartItem> items = cartService.getItems();
        for(CartItem it : items){
            listModel.addElement(it.getBook().getTitle() + " x" + it.getQuantity() + " = " + it.getTotal() + " DA");
        }
        totalLabel = totalLabel == null ? new JLabel() : totalLabel;
        totalLabel.setText("Total: " + cartService.getTotal() + " DA");
    }
}
