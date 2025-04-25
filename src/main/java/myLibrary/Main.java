package main.java.myLibrary;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        UserCreator adminFactory = new AdminCreator(); 
        UserCreator readerFactory = new ReaderCreator();
        
        User akram = adminFactory.createUser(0, "akram" , "ahmad@gmail.com");
        User lyes = readerFactory.createUser(1, "aziz" , "kimo@gmail.com");

        List<Book> books = LibrarySingleton.getLibraryInstance().getBooks();

        for (Book book : books) {
            System.out.println(book.toString());
        }

        LibrarySingleton.getLibraryInstance().addBook(new Book(books.size() + 1, "The Catcher in the Rye", "J.D. Salinger", "Coming-of-Age", 1));
        LibrarySingleton.getLibraryInstance().addBook(new Book(books.size() + 1, "The Catcher in the Rye", "J.D. Salinger", "Coming-of-Age", 1));

        System.out.println("========================================= after adding a book");

        for (Book book : books) {
            System.out.println(book.toString());
        }

        System.out.println("========================searching about a book by title");
        SearchContext searchContext = new SearchContext();
        searchContext.setStrategyMethod(new SearchByTitle());
        List<Book> result = searchContext.search(books, "The in the Rye");

        for(Book book : result) {
            System.out.println(book.toString());
        }
    }
}
