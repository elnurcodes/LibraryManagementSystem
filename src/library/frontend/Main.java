package library.frontend;

import library.backend.Book;
import library.backend.Library;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a Library instance
        Library library = new Library();

        // Sample data
        library.addBook(new Book("1984", "George Orwell", "123456789"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "987654321"));

        // Interact with the user (CLI example)
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Display Books");
            System.out.println("2. Add Book");
            System.out.println("3. Remove Book");
            System.out.println("4. Borrow Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    library.addBook(new Book(title, author, isbn));
                    break;
                case 3:
                    System.out.print("Enter ISBN of the book to remove: ");
                    String removeIsbn = scanner.nextLine();
                    library.removeBook(removeIsbn);
                    break;
                case 4:
                    System.out.print("Enter ISBN of the book to borrow: ");
                    String borrowIsbn = scanner.nextLine();
                    if (!library.borrowBook(borrowIsbn)) {
                        System.out.println("Book not available or already borrowed.");
                    }
                    break;
                case 5:
                    System.out.print("Enter ISBN of the book to return: ");
                    String returnIsbn = scanner.nextLine();
                    if (!library.returnBook(returnIsbn)) {
                        System.out.println("Book not borrowed or doesn't exist.");
                    }
                    break;
                case 6:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
