package main.java.myLibrary;

// factory method pattern to manage users

public abstract class User {
    private int id;
    private String name;
    private String email;

    public User( int id, String name, String email) {
        this.id = id; 
        this.name =  name;
        this.email = email;
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
}

class Admin extends User {
    private boolean isAdmin;
    public Admin(int id, String name, String email) {
        super(id, name, email);
        this.isAdmin = true;
    }
}

class Reader extends User {
    private boolean isAdmin;
    public Reader(int id, String name, String email) {
        super(id, name, email);
        this.isAdmin = false;
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



