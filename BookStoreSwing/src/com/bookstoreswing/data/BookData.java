package com.bookstoreswing.data;

import java.util.*;
import com.bookstoreswing.model.Book;

/**
 * Simple in-memory data provider for books
 */
public class BookData {
    public static List<Book> getSampleBooks() {
        List<Book> list = new ArrayList<>();
        list.add(new Book("B001","Java: From Zero to Hero","Ali Ahmed",3500,""));
        list.add(new Book("B002","Clean Code","Robert C. Martin",4200,""));
        list.add(new Book("B003","Effective Java","Joshua Bloch",3900,""));
        list.add(new Book("B004","Algorithms in Java","Thomas H. Cormen",4500,""));
        list.add(new Book("B005","Design Patterns","Erich Gamma",4100,""));
        list.add(new Book("B006","The Pragmatic Programmer","Andrew Hunt",3700,""));
        return list;
    }
}
