Package Library;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static LibraryService libraryService = new LibraryService();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        addBook();
                        break;
                    case 2:
                        viewAllBooks();
                        break;
                    case 3:
                        searchBook();
                        break;
                    case 4:
                        updateBook();
                        break;
                    case 5:
                        deleteBook();
                        break;
                    case 6:
                        issueBook();
                        break;
                    case 7:
                        returnBook();
                        break;
                    case 8:
                        running = false;
                        System.out.println("Exiting the Library Management System. Goodbye!");
                        break;
                    default:
                        throw new BookException("Invalid menu choice. Please enter a number between 1 and 8.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input. Please enter a valid number for the menu choice.");
            } catch (BookException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("========= Library Management System =========");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Book");
        System.out.println("4. Update Book");
        System.out.println("5. Delete Book");
        System.out.println("6. Issue Book");
        System.out.println("7. Return Book");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addBook() {
        try {
            System.out.print("Enter Book ID: ");
            int bookId = readInt();

            System.out.print("Enter Title: ");
            String title = scanner.nextLine().trim();

            System.out.print("Enter Author: ");
            String author = scanner.nextLine().trim();

            libraryService.addBook(bookId, title, author);
            System.out.println("Book added successfully!");
        } catch (BookException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. Book ID must be a number.");
        }
    }

    private static void viewAllBooks() {
        ArrayList<Book> books = libraryService.viewAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books are currently available in the library.");
        } else {
            System.out.println("----- List of All Books -----");
            for (Book book : books) {
                System.out.println(book);
                System.out.println("------------------------------");
            }
        }
    }

    private static void searchBook() {
        try {
            System.out.print("Enter Book ID to search: ");
            int bookId = readInt();

            Book book = libraryService.searchBook(bookId);
            System.out.println("Book found:");
            System.out.println(book);
        } catch (BookException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. Book ID must be a number.");
        }
    }

    private static void updateBook() {
        try {
            System.out.print("Enter Book ID to update: ");
            int bookId = readInt();

            System.out.print("Enter new Title: ");
            String title = scanner.nextLine().trim();

            System.out.print("Enter new Author: ");
            String author = scanner.nextLine().trim();

            libraryService.updateBook(bookId, title, author);
            System.out.println("Book updated successfully!");
        } catch (BookException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. Book ID must be a number.");
        }
    }

    private static void deleteBook() {
        try {
            System.out.print("Enter Book ID to delete: ");
            int bookId = readInt();

            libraryService.deleteBook(bookId);
            System.out.println("Book deleted successfully!");
        } catch (BookException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. Book ID must be a number.");
        }
    }

    private static void issueBook() {
        try {
            System.out.print("Enter Book ID to issue: ");
            int bookId = readInt();

            libraryService.issueBook(bookId);
            System.out.println("Book issued successfully!");
        } catch (BookException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. Book ID must be a number.");
        }
    }

    private static void returnBook() {
        try {
            System.out.print("Enter Book ID to return: ");
            int bookId = readInt();

            libraryService.returnBook(bookId);
            System.out.println("Book returned successfully!");
        } catch (BookException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. Book ID must be a number.");
        }
    }

    // Helper to read an integer from the console, trimming whitespace.
    // Throws NumberFormatException if the input is not a valid integer.
    private static int readInt() {
        String line = scanner.nextLine().trim();
        return Integer.parseInt(line);
    }
}
