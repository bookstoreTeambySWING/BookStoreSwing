package com.bookstoreswing.model;

/**
 * Item in cart with quantity
 */
public class CartItem {
    private Book book;
    private int quantity;

    public CartItem(Book book, int quantity) {
        this.book = book; this.quantity = quantity;
    }

    public Book getBook(){ return book; }
    public int getQuantity(){ return quantity; }
    public void setQuantity(int q){ this.quantity = q; }
    public double getTotal(){ return book.getPrice() * quantity; }
}
