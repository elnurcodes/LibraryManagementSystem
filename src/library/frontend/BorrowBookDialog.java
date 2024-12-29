package library.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BorrowBookDialog extends JDialog {

    private JTextField isbnField;
    private JButton borrowButton, cancelButton;

    public BorrowBookDialog(JFrame parent) {
        super(parent, "Borrow Book", true);  // true makes the dialog modal
        setLayout(new GridLayout(2, 2, 10, 10));

        // Add form elements
        add(new JLabel("ISBN:"));
        isbnField = new JTextField();
        add(isbnField);

        borrowButton = new JButton("Borrow");
        cancelButton = new JButton("Cancel");
        add(borrowButton);
        add(cancelButton);

        // Action listener for borrow button
        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isbn = isbnField.getText();
                // Correct way: Use the instance of Library class to borrow the book
                boolean success = LibraryApp.library.borrowBook(isbn);
                if (success) {
                    JOptionPane.showMessageDialog(parent, "Book borrowed successfully!");
                } else {
                    JOptionPane.showMessageDialog(parent, "Book not found or already borrowed.");
                }
                dispose();  // Close the dialog after action
            }
        });

        // Action listener for cancel button
        cancelButton.addActionListener(e -> dispose());  // Close the dialog without action

        setSize(300, 150);  // Set dialog size
        setLocationRelativeTo(parent);  // Center the dialog over the parent
    }
}
