package main.java.myLibrary;

// singleton pattern for the library
public class LibrarySingleton {
    private static LibrarySingleton instance = null;

    private LibrarySingleton() {}

    public LibrarySingleton getLibraryInstance() {
        if(instance == null) {
            instance = new LibrarySingleton();
        }
        return instance;
    }
}
