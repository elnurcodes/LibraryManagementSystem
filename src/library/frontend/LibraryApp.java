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
        displayBooksButton.addActionListener(e -> System.out.println("Display Books clicked"));

        setLocationRelativeTo(null); // Center the window on the screen
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryApp app = new LibraryApp();
            app.setVisible(true);
        });
    }
}
