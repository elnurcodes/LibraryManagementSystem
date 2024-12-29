package library.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Library {

    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        for (Book b : books) {
            if (b.getIsbn().equals(book.getIsbn())) {
                System.out.println("Error: A book with this ISBN already exists.");
                return;
            }
        }
        books.add(book);
    }

    // Get all books in the library
    public List<Book> getBooks() {
        return books;
    }

    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    public boolean borrowBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && !book.isBorrowed()) {
                book.setBorrowed(true);
                return true;
            }
        }
        System.out.println("Error: Book with ISBN " + isbn + " not available for borrowing.");
        return false;
    }

    public boolean returnBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && book.isBorrowed()) {
                book.setBorrowed(false);
                return true;
            }
        }
        System.out.println("Error: Book with ISBN " + isbn + " is not currently borrowed.");
        return false;
    }

    // Sort books by title
    public void sortBooksByTitle() {
        Collections.sort(books, Comparator.comparing(Book::getTitle));
    }

    // Sort books by author
    public void sortBooksByAuthor() {
        Collections.sort(books, Comparator.comparing(Book::getAuthor));
    }

    public void displayBooks() {
        books.sort((b1, b2) -> b1.getTitle().compareTo(b2.getTitle())); // Sort by title
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
