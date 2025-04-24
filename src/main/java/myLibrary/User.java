package main.java.myLibrary;

// factory method pattern to manage users

public interface User {    
       
}

class Admin implements User {

}

class Reader implements User {
    
}

// Creator
abstract class UserCreator {
    abstract User createUser();
}

// ConcreteCreators 
class AdminCreator extends UserCreator {
    @Override
    User createUser() {
        return new Admin();
    }
}

class ReaderCreator extends UserCreator {
    @Override
    User createUser() {
        return new Reader();
    }
}



