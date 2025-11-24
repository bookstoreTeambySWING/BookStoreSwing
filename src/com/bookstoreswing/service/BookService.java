package com.bookstoreswing.service;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import com.bookstoreswing.model.Book;
import com.bookstoreswing.data.BookData;

public class BookService {

    // Return ALL books from all categories
    public List<Book> getAllBooks() {
        return BookData.getAllBooksWithCategories();
    }

    // Return books filtered by category
    public List<Book> getBooksByCategory(String category) {

        // If "All" → return everything
        if (category.equalsIgnoreCase("All") || category.equalsIgnoreCase("All books")) {
            return getAllBooks();
        }

        // Filter by exact category name (case-insensitive)
        return getAllBooks()
                .stream()
                .filter(b -> b.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Dynamic categories list from actual BookData
    public List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("All"); // always include "All" first

        // Extract categories dynamically
        for (Book b : getAllBooks()) {
            if (!b.getCategory().isEmpty() && !categories.contains(b.getCategory())) {
                categories.add(b.getCategory());
            }
        }

        return categories;
    }
}
