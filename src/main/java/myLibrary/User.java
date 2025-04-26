package main.java.myLibrary;

import java.util.ArrayList;
import java.util.List;

// factory method pattern to manage users

public abstract class User {
    private int id;
    private String name;
    private String email;
    private List<Book> borrowedBooks;

    public User( int id, String name, String email) {
        this.id = id; 
        this.name =  name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void addBorrowedBook(Book newBook) {
        for(Book book: borrowedBooks) {
            if(book.equals(newBook)) {
                book.incrementQuantity();
                return;
            }
        }

        borrowedBooks.add(newBook);
    }

    abstract boolean getIsAdmin();

    @Override 
    public String toString() {
        return "id:" + this.id + "\nname: "+ this.name + "\nemail: "+this.email +"\nis admin:"+ this.getIsAdmin();
    }
}

class Admin extends User {
    public Admin(int id, String name, String email) {
        super(id, name, email);
    }

    @Override 
    boolean getIsAdmin() {
        return true;
    }
}

class Reader extends User {
    public Reader(int id, String name, String email) {
        super(id, name, email);
    }

    @Override
    boolean getIsAdmin() {
        return false;
    }
}

// Creator
abstract class UserCreator {
    abstract User createUser(int id, String name, String email);
}

// ConcreteCreators 
class AdminCreator extends UserCreator {
    @Override
    User createUser(int id, String name, String email) {
        return new Admin(id, name, email);
    }
}

class ReaderCreator extends UserCreator {
    @Override
    User createUser(int id, String name, String email) {
        return new Reader(id, name, email);
    }
}



