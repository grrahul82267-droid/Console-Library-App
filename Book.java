// Book.java
public class Book {
    private final int id;
    private String title;
    private String author;
    private boolean isBorrowed;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    // Encapsulation: getters/setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public boolean isBorrowed() { return isBorrowed; }

    public void borrow() {
        if (isBorrowed) throw new IllegalStateException("Book already borrowed.");
        isBorrowed = true;
    }

    public void giveBack() {
        if (!isBorrowed) throw new IllegalStateException("Book was not borrowed.");
        isBorrowed = false;
    }

    @Override
    public String toString() {
        return String.format("[%d] \"%s\" by %s %s", id, title, author, isBorrowed ? "(Borrowed)" : "");
    }
}
