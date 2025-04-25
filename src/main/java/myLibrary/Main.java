package main.java.myLibrary;

import java.util.ArrayList;
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

        LibrarySingleton.getLibraryInstance().addBook(new Book(5, "The Catcher in the Rye", "J.D. Salinger", "Coming-of-Age", 1));
    }
}
