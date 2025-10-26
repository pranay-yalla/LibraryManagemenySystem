import java.util.*;

class Book {
    private int id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }

    public void issue() { isIssued = true; }
    public void returnBook() { isIssued = false; }

    @Override
    public String toString() {
        return "ID: " + id + " | " + title + " by " + author +
               (isIssued ? " [Issued]" : " [Available]");
    }
}

public class LibrarySystem {
    private static List<Book> books = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static int nextId = 1;

    public static void main(String[] args) {
        System.out.println("📚 Welcome to Simple Library System!");
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("❌ Please enter a valid number!");
                continue;
            }

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewBooks();
                case 3 -> issueBook();
                case 4 -> returnBook();
                case 5 -> {
                    System.out.println("✅ Exiting... Thank you!");
                    return;
                }
                default -> System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = sc.nextLine();
        System.out.print("Enter author name: ");
        String author = sc.nextLine();

        books.add(new Book(nextId++, title, author));
        System.out.println("✅ Book added successfully!");
    }

    private static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            
            return;
        }
        System.out.println("\n📖 Book List:");
        for (Book b : books)
            System.out.println(b);
    }

    private static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int id = Integer.parseInt(sc.nextLine());

        for (Book b : books) {
            if (b.getId() == id && !b.isIssued()) {
                b.issue();
                System.out.println("✅ Book issued successfully!");
                return;
            }
        }
        System.out.println("❌ Book not found or already issued.");
    }

    private static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = Integer.parseInt(sc.nextLine());

        for (Book b : books) {
            if (b.getId() == id && b.isIssued()) {
                b.returnBook();
                System.out.println("✅ Book returned successfully!");
                return;
            }
        }
        System.out.println("❌ Book not found or not issued.");
    }
}