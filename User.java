// User.java
import java.util.ArrayList;
import java.util.List;

public class User {
    private final int userId;
    private final String name;
    private final List<Book> borrowedBooks;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getUserId() { return userId; }
    public String getName() { return name; }

    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public boolean returnBook(Book book) {
        return borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return String.format("User %d: %s (Borrowed: %d)", userId, name, borrowedBooks.size());
    }
}
