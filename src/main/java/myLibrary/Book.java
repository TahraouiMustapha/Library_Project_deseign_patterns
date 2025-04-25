package main.java.myLibrary;

public class Book {
    private int id;
    private  String title;
    private  String author;
    private  String category;
    private int quantity; 

    public Book(int id, String title, String author, String category, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.quantity = quantity;
    }
    
    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public void incrementQuantity() {
        this.quantity = this.quantity + 1;
    }

    public boolean decrementQuantity() {
        if (this.quantity > 0) {
            this.quantity = this.quantity - 1;
            return true;
        }
        return false;
    }

    @Override 
    public String toString() {
        return "Book:" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", author='" + author + '\'' +
            ", category='" + category + '\'' +
            ", quantity=" + quantity ;
    }
}
