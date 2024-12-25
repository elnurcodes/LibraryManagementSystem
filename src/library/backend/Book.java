package library.backend;

//Class definition
public class Book {

    //Attributes
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowed;

    //Constructor
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false; //Default state
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isBorrowed() {
        return isBorrowed; // Returns the borrowing status
    }

    public void setBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed; // Updates the borrowing status
    }

    // Optional: Override toString() for better display
    @Override
    public String toString() {
        return "Book [Title=" + title + ", Author=" + author + ", ISBN=" + isbn + ", Borrowed=" + isBorrowed + "]";
    }
}
