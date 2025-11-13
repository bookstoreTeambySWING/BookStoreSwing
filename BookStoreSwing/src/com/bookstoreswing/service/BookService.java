package com.bookstoreswing.service;

import java.util.List;
import com.bookstoreswing.model.Book;
import com.bookstoreswing.data.BookData;

/**
 * Service to retrieve books
 */
public class BookService {
    public List<Book> getAllBooks(){
        return BookData.getSampleBooks();
    }
}
