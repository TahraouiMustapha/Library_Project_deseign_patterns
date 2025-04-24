package main.java.myLibrary;

public class Main {
    public static void main(String[] args) {
        UserCreator adminFactory = new AdminCreator(); 
        UserCreator readerFactory = new ReaderCreator();
        
        User akram = adminFactory.createUser(0, "akram" , "ahmad@gmail.com");
        User lyes = readerFactory.createUser(1, "aziz" , "kimo@gmail.com");
        System.out.println(akram.getName()+ "and , " + lyes.getEmail());
    }
}
