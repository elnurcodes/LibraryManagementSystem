package library.frontend;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryApp extends JFrame {

    public static library.backend.Library library; // Shared library instance
    private JButton addBookButton, removeBookButton, borrowBookButton, returnBookButton, displayBooksButton, sortByTitleButton, sortByAuthorButton;

    public LibraryApp() {
        // Initialize the shared library instance
        library = new library.backend.Library();

        // Set up the main JFrame
        setTitle("Library Management System");
        setSize(700, 500); // Increased size for better layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create buttons
        addBookButton = new JButton("Add Book");
        removeBookButton = new JButton("Remove Book");
        borrowBookButton = new JButton("Borrow Book");
        returnBookButton = new JButton("Return Book");
        displayBooksButton = new JButton("Display Books");
        sortByTitleButton = new JButton("Sort by Title");
        sortByAuthorButton = new JButton("Sort by Author");

        // Button Styling: Set fonts, background, and foreground colors for buttons
        addBookButton.setFont(new Font("Arial", Font.BOLD, 14));
        addBookButton.setBackground(new Color(100, 149, 237)); // Light blue color
        addBookButton.setForeground(Color.WHITE);

        removeBookButton.setFont(new Font("Arial", Font.BOLD, 14));
        removeBookButton.setBackground(new Color(255, 99, 71)); // Tomato red
        removeBookButton.setForeground(Color.WHITE);

        borrowBookButton.setFont(new Font("Arial", Font.BOLD, 14));
        returnBookButton.setFont(new Font("Arial", Font.BOLD, 14));
        displayBooksButton.setFont(new Font("Arial", Font.BOLD, 14));
        sortByTitleButton.setFont(new Font("Arial", Font.BOLD, 14));
        sortByAuthorButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Tooltips for buttons
        addBookButton.setToolTipText("Click to add a new book to the library");
        removeBookButton.setToolTipText("Click to remove a book by ISBN");
        borrowBookButton.setToolTipText("Click to borrow a book");
        returnBookButton.setToolTipText("Click to return a borrowed book");
        displayBooksButton.setToolTipText("Click to view all books in the library");
        sortByTitleButton.setToolTipText("Click to sort books by title");
        sortByAuthorButton.setToolTipText("Click to sort books by author");

        // Add buttons to a JPanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(7, 1, 10, 10)); // Updated layout for removed buttons
        buttonPanel.add(addBookButton);
        buttonPanel.add(removeBookButton);
        buttonPanel.add(borrowBookButton);
        buttonPanel.add(returnBookButton);
        buttonPanel.add(displayBooksButton);
        buttonPanel.add(sortByTitleButton);
        buttonPanel.add(sortByAuthorButton);

        // Add action listeners for buttons
        addBookButton.addActionListener(e -> new AddBookDialog(LibraryApp.this).setVisible(true)); // Add Book Dialog
        removeBookButton.addActionListener(e -> showRemoveBookDialog()); // Show Remove Book Dialog
        borrowBookButton.addActionListener(e -> new BorrowBookDialog(LibraryApp.this).setVisible(true)); // Borrow Book Dialog
        returnBookButton.addActionListener(e -> new ReturnBookDialog(LibraryApp.this).setVisible(true)); // Return Book Dialog
        displayBooksButton.addActionListener(e -> displayBooks(library.getBooks())); // Show all books by default

        // Sorting action listeners
        sortByTitleButton.addActionListener(e -> {
            library.sortBooksByTitle();
            displayBooks(library.getBooks());
        });

        sortByAuthorButton.addActionListener(e -> {
            library.sortBooksByAuthor();
            displayBooks(library.getBooks());
        });

        // Add search bar and button
        JTextField searchField = new JTextField(15);
        JButton searchButton = new JButton("Search");
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Add action listener for search
        searchButton.addActionListener(e -> {
            String query = searchField.getText().trim().toLowerCase();
            if (query.isEmpty()) {
                displayBooks(library.getBooks()); // Show all books if search is empty
            } else {
                List<library.backend.Book> filteredBooks = library.getBooks().stream()
                        .filter(book -> book.getTitle().toLowerCase().contains(query)
                        || book.getAuthor().toLowerCase().contains(query))
                        .collect(Collectors.toList());
                displayBooks(filteredBooks); // Show filtered books
            }
        });

        // Add panels to the frame
        add(searchPanel, BorderLayout.NORTH); // Search panel at the top
        add(buttonPanel, BorderLayout.CENTER); // Buttons in the center

        setLocationRelativeTo(null); // Center the window on the screen
    }

    // Updated method to display books in a JTable
    private void displayBooks(List<library.backend.Book> books) {
        JTable bookTable = new JTable(new BookTableModel(books));
        JScrollPane scrollPane = new JScrollPane(bookTable);
        bookTable.setFillsViewportHeight(true);

        // Create a dialog to show the table
        JDialog dialog = new JDialog(this, "Library Books", true);
        dialog.setSize(600, 400);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // Method to show Remove Book Dialog
    private void showRemoveBookDialog() {
        // Ask the user for the ISBN of the book to remove
        String isbn = JOptionPane.showInputDialog(this, "Enter ISBN of the book to remove:");

        if (isbn != null && !isbn.trim().isEmpty()) {
            // Try to remove the book from the library
            boolean success = library.removeBook(isbn.trim());
            if (success) {
                JOptionPane.showMessageDialog(this, "Book removed successfully.");
                displayBooks(library.getBooks()); // Refresh the table
            } else {
                JOptionPane.showMessageDialog(this, "Error: No book found with ISBN " + isbn);
            }
        } else {
            JOptionPane.showMessageDialog(this, "ISBN cannot be empty.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryApp app = new LibraryApp();
            app.setVisible(true);
        });
    }
}
