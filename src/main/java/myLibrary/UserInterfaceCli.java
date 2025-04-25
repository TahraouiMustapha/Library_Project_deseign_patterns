package main.java.myLibrary;

import java.util.Scanner;

public class UserInterfaceCli {
    private Scanner scanner = new Scanner(System.in);

    UserCreator adminFactory = new AdminCreator(); 
    UserCreator readerFactory = new ReaderCreator();

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
            userName = scanner.nextLine();
            userEmail = scanner.nextLine();
            adminFactory.createUser(id, userName, userEmail);
        } else if(choice == 2){
            System.out.println("=======================\nSign up as reader");
            userName = scanner.nextLine();
            userEmail = scanner.nextLine();
            readerFactory.createUser(id, userName, userEmail);
        } else {
            System.out.println("repeat your answer");
            signUp();
        }

    }

    public void signIn() {
        System.out.println("=====================\n Sign In");
    }

    

}
