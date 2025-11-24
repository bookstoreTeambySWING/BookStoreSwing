package com.bookstoreswing.data;

import java.util.*;
import com.bookstoreswing.model.Book;

public class BookData {

    public static List<Book> getRomanceBooks() {
        List<Book> list = new ArrayList<>();

        list.add(new Book("H001", 
                "Guerres du monde émergé, Tome 3 : Un nouveau règne", 
                "Licia Troisi", 
                2500,
                "covers/book1.jpg",
                "Historical"));

        list.add(new Book("H002", 
                "Juniper & Thorn", 
                "Ava Reid", 
                2205,
                "covers/book8.jpg",
                "Historical"));

        list.add(new Book("H003", 
                "The Georgian Rake: An intriguing historical romance", 
                "Alice Chetwynd Ley", 
               2350,
                "covers/16.jpg",
                "Historical"));

        list.add(new Book("H004", 
                "La jeune fille à la perle", 
                "Tracy Chevalier", 
                707,
                "covers/book5.jpg",
                "Historical"));

        list.add(new Book("H005", 
                "JLes Larmes Rouges", 
                "Georgia Caldera", 
                2099,
                "covers/19.jpg",
                "Historicale"));

        return list;
    }

    public static List<Book> getHistoricalBooks() {
        List<Book> list = new ArrayList<>();

        list.add(new Book("R001", 
                "Outlander, Tome 2 : Le Talisman", 
                "Diana Gabaldon", 
                1890,
                "covers/book3.jpg",
                "Romance"));

        list.add(new Book("R002", 
                "Outlander, Tome 4 : Les Tambours de l'automne", 
                "Diana Gabaldon", 
                1890,
                "covers/book4.jpg",
                "Romance"));

        list.add(new Book("R003", 
                "Outlander, Tome 1 : Le Chardon et le Tartan", 
                "Diana Gabaldon", 
                1715,
                "covers/book6.jpg",
                "Romance"));

        list.add(new Book("R004", 
               "Tome 2 : Un songe illusoire", 
                "Ava Reid", 
                2799,
                "covers/book2.jpg",
                "Romance"));

        return list;
    }

    public static List<Book> getFantasyBooks() {
        List<Book> list = new ArrayList<>();

        list.add(new Book("F001",
                "Panorama illustré de la fantasy et du merveilleux",
                "André-François Ruaud",
                1860,
                "covers/book12.jpg",
                "Fantasy"
        ));

        list.add(new Book("F002",
                "La Légende Final Fantasy VII",
                "Nicolas Courcoler",
                2350,
                "covers/book13.jpg",
                "Fantasy"
        ));

        list.add(new Book("F003",
                "Le langage de la nuit - Essais sur la science-fiction et la fantasy",
                "Ursula K. Le Guin",
                1480,
                "covers/book1.jpg",
                "Fantasy"
        ));

        list.add(new Book("F004",
                "Dictionnaire de la fantasy",
                "Anne Besson",
                1750,
                "covers/14.jpg",
                "Fantasy"
        ));

        list.add(new Book("F005",
                "L'Impératrice remariée, Tome 6",
                "Alphatart",
                1420,
                "covers/15.jpg",
                "Fantasy"
        ));

    
        return list;
    }

    public static List<Book> getGuerreBooks() {
        List<Book> list = new ArrayList<>();

        list.add(new Book("G001",
                "Prince captif",
                "C.S. Pascal",
                1750,
                "covers/book9.jpg",
                "Guerre"
        ));


        list.add(new Book("G003",
                "Prince captif, Tome 3 : Le Roi, suivi de Le Palais d'été",
                "C.S. Pascal",
                2080,
                "covers/book10.jpg",
                "Guerre"
        ));

        list.add(new Book("G004",
                "Dent d'ours, Tome 2 : Hanna",
                "Yann & Alain Henriet",
                1650,
                "covers/20.jpg",
                "Guerre"
        ));

        list.add(new Book("G005",
                "La Guerre du pavot, Tome 1",
                "Rebecca Kuang",
                1350,
                "covers/18.jpg",
                "Guerre"
        ));

        list.add(new Book("G006",
                "Guerre",
                "Louis-Ferdinand Céline",
                2080,
                "covers/17.jpg",
                "Guerre"
        ));

     
        return list;
    }

    public static List<Book> getProgrammingBooks() {
        List<Book> list = new ArrayList<>();

        list.add(new Book("P001", 
                "Effective Java", 
                "Joshua Bloch", 
                3900,
                "covers/book1.jpg", 
                "Programming"));

        list.add(new Book("P002", 
                "Algorithms in Java", 
                "Thomas H. Cormen", 
                4500,
                "covers/book2.jpg", 
                "Programming"));

        list.add(new Book("P003", 
                "Design Patterns", 
                "Erich Gamma", 
                4100,
                "covers/book3.jpg", 
                "Programming"));

        return list;
    }

    public static List<Book> getAllBooksWithCategories() {
        List<Book> allBooks = new ArrayList<>();
        allBooks.addAll(getRomanceBooks());
        allBooks.addAll(getHistoricalBooks());
        allBooks.addAll(getFantasyBooks());
        allBooks.addAll(getGuerreBooks());
        allBooks.addAll(getProgrammingBooks());
        return allBooks;
    }

    // Backward compatibility
    public static List<Book> getAllBooks() {
        return getAllBooksWithCategories();
    }

    // Helper method to get books by category
    public static List<Book> getBooksByCategory(String category) {
        if (category == null) return getAllBooks();
        
        switch (category.toLowerCase()) {
            case "romance":
                return getRomanceBooks();
            case "historical":
                return getHistoricalBooks();
            case "fantasy":
                return getFantasyBooks();
            case "guerre":
                return getGuerreBooks();
            case "programming":
                return getProgrammingBooks();
            case "all":
            default:
                return getAllBooks();
        }
    }
}