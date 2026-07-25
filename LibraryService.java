Package Library;
import java.util.ArrayList;


public class LibraryService {

    private ArrayList<Book> books;

    public LibraryService() {
        books = new ArrayList<>();
    }

    // 1. Add a new book. Throws BookException if the Book ID already exists.
    public void addBook(int bookId, String title, String author) throws BookException {
        if (findBookById(bookId) != null) {
            throw new BookException("A book with ID " + bookId + " already exists.");
        }
        Book book = new Book(bookId, title, author);
        books.add(book);
    }

    // 2. Return all books currently stored.
    public ArrayList<Book> viewAllBooks() {
        return books;
    }

    // 3. Search for a book by ID. Throws BookException if not found.
    public Book searchBook(int bookId) throws BookException {
        Book book = findBookById(bookId);
        if (book == null) {
            throw new BookException("No book found with ID " + bookId + ".");
        }
        return book;
    }

    // 4. Update the title and author of an existing book.
    public void updateBook(int bookId, String newTitle, String newAuthor) throws BookException {
        Book book = findBookById(bookId);
        if (book == null) {
            throw new BookException("No book found with ID " + bookId + ".");
        }
        book.setTitle(newTitle);
        book.setAuthor(newAuthor);
    }

    // 5. Delete a book by ID.
    public void deleteBook(int bookId) throws BookException {
        Book book = findBookById(bookId);
        if (book == null) {
            throw new BookException("No book found with ID " + bookId + ".");
        }
        books.remove(book);
    }

    // 6. Issue a book. Throws BookException if not found or already issued.
    public void issueBook(int bookId) throws BookException {
        Book book = findBookById(bookId);
        if (book == null) {
            throw new BookException("No book found with ID " + bookId + ".");
        }
        if (book.isIssued()) {
            throw new BookException("Book ID " + bookId + " is already issued.");
        }
        book.setIssued(true);
    }

    // 7. Return a book. Throws BookException if not found or not currently issued.
    public void returnBook(int bookId) throws BookException {
        Book book = findBookById(bookId);
        if (book == null) {
            throw new BookException("No book found with ID " + bookId + ".");
        }
        if (!book.isIssued()) {
            throw new BookException("Book ID " + bookId + " was not issued, so it cannot be returned.");
        }
        book.setIssued(false);
    }

    // Helper method: find a book by its ID, or return null if not present.
    private Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }
}
