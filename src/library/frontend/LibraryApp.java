package library.frontend;

import javax.swing.*;
import java.awt.*;

public class LibraryApp extends JFrame {

    public static library.backend.Library library; // Shared library instance
    private JButton addBookButton, removeBookButton, borrowBookButton, returnBookButton, displayBooksButton;

    public LibraryApp() {
        // Initialize the shared library instance
        library = new library.backend.Library();

        // Set up the main JFrame
        setTitle("Library Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create buttons
        addBookButton = new JButton("Add Book");
        removeBookButton = new JButton("Remove Book");
        borrowBookButton = new JButton("Borrow Book");
        returnBookButton = new JButton("Return Book");
        displayBooksButton = new JButton("Display Books");

        // Add buttons to a JPanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 10));
        buttonPanel.add(addBookButton);
        buttonPanel.add(removeBookButton);
        buttonPanel.add(borrowBookButton);
        buttonPanel.add(returnBookButton);
        buttonPanel.add(displayBooksButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Add action listeners for buttons
        addBookButton.addActionListener(e -> new AddBookDialog(LibraryApp.this).setVisible(true)); // Add Book Dialog
        removeBookButton.addActionListener(e -> System.out.println("Remove Book clicked"));
        borrowBookButton.addActionListener(e -> new BorrowBookDialog(LibraryApp.this).setVisible(true));  // Borrow Book Dialog
        returnBookButton.addActionListener(e -> new ReturnBookDialog(LibraryApp.this).setVisible(true));  // Return Book Dialog
        displayBooksButton.addActionListener(e -> displayBooks()); // Action for Display Books

        setLocationRelativeTo(null); // Center the window on the screen
    }

    // Method to display books in a JTable
    private void displayBooks() {
        JTable bookTable = new JTable(new BookTableModel(library.getBooks()));
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryApp app = new LibraryApp();
            app.setVisible(true);
        });
    }
}
