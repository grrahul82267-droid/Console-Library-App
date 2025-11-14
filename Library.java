// Library.java
import java.util.*;

public class Library {
    private final Map<Integer, Book> books = new HashMap<>();
    private final Map<Integer, User> users = new HashMap<>();
    private int nextBookId = 1;
    private int nextUserId = 1;

    // Add initial books quickly
    public void addBook(String title, String author) {
        Book b = new Book(nextBookId++, title, author);
        books.put(b.getId(), b);
    }

    public Collection<Book> listAllBooks() {
        return books.values();
    }

    public Book findBookById(int id) {
        return books.get(id);
    }

    public User createUser(String name) {
        User u = new User(nextUserId++, name);
        users.put(u.getUserId(), u);
        return u;
    }

    public User findUserById(int id) {
        return users.get(id);
    }

    // Borrowing logic
    public String borrowBook(int userId, int bookId) {
        User user = users.get(userId);
        Book book = books.get(bookId);
        if (user == null) return "User not found.";
        if (book == null) return "Book not found.";
        if (book.isBorrowed()) return "Book already borrowed by someone else.";
        book.borrow();
        user.borrowBook(book);
        return "Book borrowed successfully.";
    }

    public String returnBook(int userId, int bookId) {
        User user = users.get(userId);
        Book book = books.get(bookId);
        if (user == null) return "User not found.";
        if (book == null) return "Book not found.";
        if (!book.isBorrowed()) return "Book is not currently borrowed.";
        boolean removed = user.returnBook(book);
        if (!removed) return "This user didn't borrow that book.";
        book.giveBack();
        return "Book returned successfully.";
    }
}
