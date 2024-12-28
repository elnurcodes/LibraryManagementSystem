package library.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookDialog extends JDialog {

    private JTextField titleField;
    private JTextField authorField;
    private JTextField isbnField;
    private JButton saveButton;
    private JButton cancelButton;

    public AddBookDialog(JFrame parent) {
        super(parent, "Add Book", true);
        setLayout(new GridLayout(4, 2, 10, 10));

        // Add form elements
        add(new JLabel("Title:"));
        titleField = new JTextField();
        add(titleField);

        add(new JLabel("Author:"));
        authorField = new JTextField();
        add(authorField);

        add(new JLabel("ISBN:"));
        isbnField = new JTextField();
        add(isbnField);

        saveButton = new JButton("Save");
        cancelButton = new JButton("Cancel");
        add(saveButton);
        add(cancelButton);

        // Action listeners for buttons
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                String isbn = isbnField.getText();

                // Validate the fields
                if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
                    JOptionPane.showMessageDialog(AddBookDialog.this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Create a new Book object and add it to the backend library
                    library.backend.Book newBook = new library.backend.Book(title, author, isbn);

                    // Reference the static library instance in LibraryApp
                    LibraryApp.library.addBook(newBook);

                    // Provide feedback to the user
                    JOptionPane.showMessageDialog(AddBookDialog.this, "Book added successfully!");

                    // Close the dialog
                    dispose();
                }
            }
        });

        cancelButton.addActionListener(e -> dispose());

        setSize(300, 200);
        setLocationRelativeTo(parent);
    }
}
