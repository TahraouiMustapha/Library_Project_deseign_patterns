package main.java.myLibrary;

import java.util.ArrayList;
import java.util.List;

// strategy pattern to search in books by using multiple methods
public interface SearchStrategy {
    public List<Book> search(List<Book> books, String wordSearch);
}

class SearchByTitle implements SearchStrategy {
    @Override
    public List<Book> search(List<Book> books, String wordSearch) {
        List<Book> result = new ArrayList<>();
        for(Book book: books) {
            if(book.getTitle().equals(wordSearch)) {
                result.add(book);
            }
        }
        return result;
    }
}

class SearchByAuthor implements SearchStrategy {
    @Override
    public List<Book> search(List<Book> books, String wordSearch) {
        List<Book> result = new ArrayList<>();
        for(Book book: books) {
            if(book.getAuthor().equals(wordSearch)) {
                result.add(book);
            }
        }
        return result;
    }
}

class SearchByCategory implements SearchStrategy {
    @Override
    public List<Book> search(List<Book> books, String wordSearch) {
        List<Book> result = new ArrayList<>();
        for(Book book: books) {
            if(book.getCategory().equals(wordSearch)) {
                result.add(book);
            }
        }
        return result;
    }
}

class SearchContext {
    private SearchStrategy strategy;

    public void setStrategyMethod(SearchStrategy s) {
        strategy = s;
    }

    public List<Book> search(List<Book> books, String wordSearch) {
        return strategy.search(books, wordSearch);
    }
     
}