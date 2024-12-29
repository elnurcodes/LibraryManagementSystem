package library.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReturnBookDialog extends JDialog {

    private JTextField isbnField;
    private JButton returnButton, cancelButton;

    public ReturnBookDialog(JFrame parent) {
        super(parent, "Return Book", true);  // true makes the dialog modal
        setLayout(new GridLayout(2, 2, 10, 10));

        // Add form elements
        add(new JLabel("ISBN:"));
        isbnField = new JTextField();
        add(isbnField);

        returnButton = new JButton("Return");
        cancelButton = new JButton("Cancel");
        add(returnButton);
        add(cancelButton);

        // Action listener for return button
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isbn = isbnField.getText();
                // Correct way: Use the instance of Library class to return the book
                boolean success = LibraryApp.library.returnBook(isbn);
                if (success) {
                    JOptionPane.showMessageDialog(parent, "Book returned successfully!");
                } else {
                    JOptionPane.showMessageDialog(parent, "Book not found or not borrowed.");
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
