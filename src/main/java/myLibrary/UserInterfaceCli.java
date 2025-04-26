package main.java.myLibrary;

import java.util.List;
import java.util.Scanner;


public class UserInterfaceCli {
    private Scanner scanner = new Scanner(System.in);

    UserCreator adminFactory = new AdminCreator(); 
    UserCreator readerFactory = new ReaderCreator();

    LibraryInterfaceForAdmin adminUi = new LibraryInterfaceForAdmin();
    LibraryInterfaceForReader readerUi = new LibraryInterfaceForReader();

    public void firstInterface() {
        System.out.println("Welcome to our Library: \n Enter your choice : \n1.sign up \n2.sign in");
        int choice = scanner.nextInt();
        if(choice == 1) {
            signUp();
        } else if(choice == 2) {
            signIn();
        } else {
            System.out.println("repeat your answer");
            firstInterface();
        } 
    }

    public void signUp() {
        System.out.println("=====================\n Sign Up");
        System.out.println("Enter Your choice: \n1.Sign up as admin \n2.Sign up as reader");
        int choice = scanner.nextInt();

        int id = LibrarySingleton.getLibraryInstance().getUsersNumber();

        String userName ;
        String userEmail ;


        if(choice == 1) {
            System.out.println("=======================\nSign up as admin");
            
            System.out.println("Enter your name: ");
            userName = scanner.nextLine();
            scanner.nextLine();
            System.out.println("Enter your email: ");
            userEmail = scanner.nextLine();
            // add an admin to library
            User newAdmin =  adminFactory.createUser(id, userName, userEmail);
            LibrarySingleton.getLibraryInstance().addAdmin(newAdmin);
            // success sign up
            LibrarySingleton.getLibraryInstance().setLogedIn(newAdmin);
            adminUi.UserInterface();

        } else if(choice == 2){
            System.out.println("=======================\nSign up as reader");
            System.out.println("Enter your name: ");
            userName = scanner.nextLine();
            scanner.nextLine();
            System.out.println("Enter your email: ");
            userEmail = scanner.nextLine();
            // add a user to library
            User newReader = readerFactory.createUser(id, userName, userEmail);
            LibrarySingleton.getLibraryInstance().addReader(newReader);
            // success sign up
            LibrarySingleton.getLibraryInstance().setLogedIn(newReader);
            readerUi.UserInterface();
        } else {
            System.out.println("repeat your answer");
            signUp();
        }

    }

    public void signIn() {
        System.out.println("=====================\n Sign In");
        String userName;
        String userEmail;

        System.out.println("Enter your name: ");
        userName = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Enter your email: ");
        userEmail = scanner.nextLine();

        if(LibrarySingleton.getLibraryInstance().checkUser(userName, userEmail)) {
            // succes sign in
            User theLogedIn = LibrarySingleton.getLibraryInstance().getUser(userName, userEmail);
            LibrarySingleton.getLibraryInstance().setLogedIn(theLogedIn);

            if (theLogedIn.getIsAdmin()) {
                adminUi.UserInterface();
            } else {
                readerUi.UserInterface();
            }
        } else {
            System.out.println("user doesn't exist");
            signIn();
        }
    }
}


interface LibraryInterfaceForUser {
    public void UserInterface();
    public void searchInterfaceCLI();
    public void choosingBook(List<Book> resultOfSearching);
}

class LibraryInterfaceForAdmin implements LibraryInterfaceForUser {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void UserInterface() {
        System.out.println("======================= Admin interface");
        System.out.println("Enter your choice: \n1.add book \n2.remove book \n3.exit");
        int choice = scanner.nextInt();
        if(choice == 1){
            int id = LibrarySingleton.getLibraryInstance().getBooks().size();
            System.out.println("Enter book title: ");
            scanner.nextLine(); // Consume the newline
            String title = scanner.nextLine();

            System.out.println("Enter book author: ");
            String author = scanner.nextLine();

            System.out.println("Enter book category: ");
            String category = scanner.nextLine();

            // Add the book to the library
            LibrarySingleton.getLibraryInstance().addBook(new Book(id, title, author, category, 0));

            System.out.println("Book added successfully!");
            UserInterface();
        } else if(choice == 2) {
            searchInterfaceCLI();
        } else if(choice == 3) {
            System.exit(0);
        } else {
            System.out.println("repeat your answer");
            UserInterface();
        }
    }

    @Override
    public void searchInterfaceCLI () {
        System.out.println("====================================== Remove a book");
        System.out.println("Enter your choice: \n1. Search by title \n2. Search by category \n3. Search by author");
        int choice = scanner.nextInt();
        scanner.nextLine();

        SearchContext searchContext = new SearchContext();
        List<Book> ourBooks = LibrarySingleton.getLibraryInstance().getBooks();
        List<Book> resultList; 
        
        if (choice == 1) {
            System.out.println("Enter the title: ");
            String title = scanner.nextLine();
            searchContext.setStrategyMethod(new SearchByTitle());
            resultList = searchContext.search(ourBooks, title);

            // showing the results
            if(resultList.size() > 0) {
                choosingBook(resultList);
            } else {
                System.out.println("no book found");
                searchInterfaceCLI();
            }
        } else if (choice == 2) {
            System.out.println("Enter the category: ");
            String category = scanner.nextLine();
            searchContext.setStrategyMethod(new SearchByCategory());
            resultList = searchContext.search(ourBooks, category);

            // showing the results
            if(resultList.size() > 0) {
                choosingBook(resultList);
            } else {
                System.out.println("no book found");
                searchInterfaceCLI();
            }

        } else if (choice == 3) {
            System.out.println("Enter the author: ");
            String author = scanner.nextLine();
            searchContext.setStrategyMethod(new SearchByAuthor());
            resultList = searchContext.search(ourBooks, author);

            // showing the results
            if(resultList.size() > 0) {
                choosingBook(resultList);
            } else {
                System.out.println("no book found");
                searchInterfaceCLI();
            }

        } else {
            System.out.println("Invalid choice, please try again.");
            searchInterfaceCLI();
        }
    }

    @Override 
    public void choosingBook(List<Book> resultOfSearching) {
        for(Book book: resultOfSearching) {
            System.out.println(book.toString());
        }

        System.out.println("Enter the ID of the book you want to remove: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); 

        boolean bookFound = false;
        for (Book book : resultOfSearching) {
            if (book.getId() == bookId) {
                LibrarySingleton.getLibraryInstance().removeBook(bookId);
                bookFound = true;
                System.out.println("Book removed !" );
                break;
            }
        }

        if (!bookFound) {
            System.out.println("Invalid ID, please try again.");
            choosingBook(resultOfSearching);
        } else {
            UserInterface();
        }
    }
}

class LibraryInterfaceForReader implements LibraryInterfaceForUser{
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void UserInterface() {
        System.out.println("================================ Reader interface");
        System.out.println("Enter your choice : \n1.borrow \n2.exit");

        int choice = scanner.nextInt();
        if(choice == 1) {
            searchInterfaceCLI();
        } else if(choice == 2) {
            System.exit(0);
        } else {
            System.out.println("repeat your answer");
            UserInterface();
        }
    }
    

    @Override 
    public void searchInterfaceCLI() {
        System.out.println("====================================== Search for a book");
        System.out.println("Enter your choice: \n1. Search by title \n2. Search by category \n3. Search by author");
        int choice = scanner.nextInt();
        scanner.nextLine();

        SearchContext searchContext = new SearchContext();
        List<Book> ourBooks = LibrarySingleton.getLibraryInstance().getBooks();
        List<Book> resultList;

        if (choice == 1) {
            System.out.println("Enter the title: ");
            String title = scanner.nextLine();
            searchContext.setStrategyMethod(new SearchByTitle());
            resultList = searchContext.search(ourBooks, title);

            if (resultList.size() > 0) {
                choosingBook(resultList);
            } else {
                System.out.println("No book found");
                searchInterfaceCLI();
            }
        } else if (choice == 2) {
            System.out.println("Enter the category: ");
            String category = scanner.nextLine();
            searchContext.setStrategyMethod(new SearchByCategory());
            resultList = searchContext.search(ourBooks, category);

            if (resultList.size() > 0) {
                choosingBook(resultList);
            } else {
                System.out.println("No book found");
                searchInterfaceCLI();
            }
        } else if (choice == 3) {
            System.out.println("Enter the author: ");
            String author = scanner.nextLine();
            searchContext.setStrategyMethod(new SearchByAuthor());
            resultList = searchContext.search(ourBooks, author);

            if (resultList.size() > 0) {
                choosingBook(resultList);
            } else {
                System.out.println("No book found");
                searchInterfaceCLI();
            }
        } else {
            System.out.println("Invalid choice, please try again.");
            searchInterfaceCLI();
        }
    }

    @Override 
    public void choosingBook(List<Book> resultOfSearching) {
        for(Book book: resultOfSearching) {
            System.out.println(book.toString());
        }

        System.out.println("Enter the ID of the book you want to borrow: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); 

        User logedIn = LibrarySingleton.getLibraryInstance().getLogedIn();
        boolean bookFound = false;

        for (Book book : resultOfSearching) {
            if (book.getId() == bookId) {
                bookFound = true;
                logedIn.addBorrowedBook(book);
                book.decrementQuantity();
                if(book.getQuantity() == 0) LibrarySingleton.getLibraryInstance().removeBook(bookId);
                System.out.println("Book borrowed !" );
                break;
            }
        }

        if (!bookFound) {
            System.out.println("Invalid ID, please try again.");
            choosingBook(resultOfSearching);
        } else {
            UserInterface();
        }

    }

}

