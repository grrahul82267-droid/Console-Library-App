// LibraryApp.java
import java.util.Scanner;

public class LibraryApp {
    private static final Scanner sc = new Scanner(System.in);
    private static final Library library = new Library();
    private static User currentUser = null;

    public static void main(String[] args) {
        seedData();
        System.out.println("=== Welcome to Library Console ===");
        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": handleCreateUser(); break;
                case "2": handleListBooks(); break;
                case "3": handleBorrowBook(); break;
                case "4": handleReturnBook(); break;
                case "5": handleShowUserInfo(); break;
                case "0": System.out.println("Goodbye! Have A Great Day!"); return;
                default: System.out.println("Invalid choice. Choose the correct choice."); break;
            }
        }
    }

    private static void seedData() {
        library.addBook("Rich Dad Poor Dad", "Robert Kiyosaki and Sharon Lechter");
        library.addBook("The Psychology of Money", "Morgan Housel");
        library.addBook("Introduction to Algorithms", "Cormen, Leiserson, Rivest");
    }

    private static void printMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Create / Switch User");
        System.out.println("2. List all books");
        System.out.println("3. Borrow a book");
        System.out.println("4. Return a book");
        System.out.println("5. Show current user info");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private static void handleCreateUser() {
        System.out.print("Enter your name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) { System.out.println("Name can't be empty."); return; }
        currentUser = library.createUser(name);
        System.out.println("User created and switched to: " + currentUser);
    }

    private static void handleListBooks() {
        System.out.println("\nBooks in library:");
        for (Book b : library.listAllBooks()) {
            System.out.println(b);
        }
    }

    private static void handleBorrowBook() {
        if (!ensureUser()) return;
        System.out.print("Enter Book ID to borrow: ");
        int id = readInt();
        System.out.println(library.borrowBook(currentUser.getUserId(), id));
    }

    private static void handleReturnBook() {
        if (!ensureUser()) return;
        System.out.print("Enter Book ID to return: ");
        int id = readInt();
        System.out.println(library.returnBook(currentUser.getUserId(), id));
    }

    private static void handleShowUserInfo() {
        if (!ensureUser()) return;
        System.out.println(currentUser);
        System.out.println("Borrowed books:");
        if (currentUser.getBorrowedBooks().isEmpty()) {
            System.out.println("  (none)");
        } else {
            for (Book b : currentUser.getBorrowedBooks()) {
                System.out.println("  " + b);
            }
        }
    }

    private static boolean ensureUser() {
        if (currentUser == null) {
            System.out.println("No user selected. Please create/switch user first (menu option 1).");
            return false;
        }
        return true;
    }

    private static int readInt() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Returning -1.");
            return -1;
        }
    }
}
