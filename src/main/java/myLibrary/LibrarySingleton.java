package main.java.myLibrary;

import java.util.ArrayList;
import java.util.List;

// singleton pattern to manage the unique library
public class LibrarySingleton {
    private static LibrarySingleton instance = null;
    private List<User> admins = new ArrayList<>();
    private List<User> readers = new ArrayList<>();
    private List<Book> books = new ArrayList<>();

    private User logedIn ;

    private LibrarySingleton() {
        // library mock database
        UserCreator adminFactory = new AdminCreator(); 
        UserCreator readerFactory = new ReaderCreator();
        
        User admin1 = adminFactory.createUser(0, "akram" , "akram@gmail.com");
        User reader1 = readerFactory.createUser(1, "kimo" , "kimo@gmail.com");
        admins.add(admin1);
        readers.add(reader1);

        books.add(new Book(1, "To Kill a Mockingbird", "Harper Lee", "Fiction", 3));
        books.add(new Book(2, "1984", "George Orwell", "Dystopian", 5));
        books.add(new Book(3, "The Great Gatsby", "F. Scott Fitzgerald", "Classic", 2));
        books.add(new Book(4, "The Hobbit", "J.R.R. Tolkien", "Fantasy", 4));
        books.add(new Book(5, "The Catcher in the Rye", "J.D. Salinger", "Coming-of-Age", 1));
    }

    public static LibrarySingleton getLibraryInstance() {
        if(instance == null) {
            instance = new LibrarySingleton();
        }
        return instance;
    }

    // add a book in library
    public void addBook(Book newBook) {
        for(Book book: books) {
            // if the book exists before
            if(book.equals(newBook)) {
                book.incrementQuantity();
                return;
            } 
        }
        books.add(newBook);
    }

    // remove a book from library
    public void removeBook(int id) {
        for (Book book : books) {
            if(book.getId() == id) {
                if(book.getQuantity() > 0) {
                    book.decrementQuantity();
                    return;
                }
                if(book.getQuantity() == 0) {
                    books.remove(book);
                    return;
                }
            }
        } 
        System.out.println("the book doesn't exist");
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<User> getAdmins() {
        return admins;
    }

    public List<User> getReaders() {
        return readers;
    }

    public User getUser(String name, String email){
        for(User admin: admins) {
            if(name.equals(admin.getName()) || email.equals(admin.getEmail())) {
                return admin;
            }
        }

        for(User reader: readers) {
            if(name.equals(reader.getName()) || email.equals(reader.getEmail())) {
                return reader;
            }
        }

        return null;
    }

    public int getUsersNumber() {
        return admins.size() + readers.size();
    }

    public void addAdmin(User newUser) {
        for(User user: admins) {
            if(user.equals(newUser)) {
                System.out.println("User exist before");
                return;
            }
        }
        admins.add(newUser);
    }

    public void addReader(User newUser) {
        for(User reader: readers) {
            if(reader.equals(newUser)) {
                System.out.println("User exist before");
                return;
            }
        }
        readers.add(newUser);
    }

    public boolean checkUser(String name, String email) {
        for(User admin: admins) {
            if(name.equals(admin.getName()) || email.equals(admin.getEmail())) {
                return true;
            }
        }

        for(User reader: readers) {
            if(name.equals(reader.getName()) || email.equals(reader.getEmail())) {
                return true;
            }
        }

        return false;
    }


    public User getLogedIn() {
        return logedIn;
    }

    public void setLogedIn(User logedIn) {
        this.logedIn = logedIn;
    }

}
