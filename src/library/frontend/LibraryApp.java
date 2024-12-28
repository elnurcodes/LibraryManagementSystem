package library.frontend;

import javax.swing.*;
import java.awt.*;

public class LibraryApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        //A panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 rows, 1 column, 10px gaps

        //Buttons
        JButton addBookButton = new JButton("Add Book");
        JButton removeBookButton = new JButton("Remove Book");
        JButton borrowBookButton = new JButton("Borrow Book");
        JButton returnBookButton = new JButton("Return Book");
        JButton displayBooksButton = new JButton("Display Books");

        //Placeholder actions
        addBookButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Add Book clicked!"));
        removeBookButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Remove Book clicked!"));
        borrowBookButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Borrow Book clicked!"));
        returnBookButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Return Book clicked!"));
        displayBooksButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Display Books clicked!"));

        //Buttons to the panel
        panel.add(addBookButton);
        panel.add(removeBookButton);
        panel.add(borrowBookButton);
        panel.add(returnBookButton);
        panel.add(displayBooksButton);

        //A panel to the frame
        frame.add(panel);

        //The window visible
        frame.setVisible(true);
    }
}
