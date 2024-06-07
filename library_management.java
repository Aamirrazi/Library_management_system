


import java.util.ArrayList;
import java.util.Scanner;
class Book {
    private String id;
    private String title;
    private String author;
    private boolean isIssued;
    private String issuedTo;
    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
        this.issuedTo = null;
    }
    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public boolean isIssued() {
        return isIssued;
    }
    public String getIssuedTo() {
        return issuedTo;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public void issueTo(String studentName) {
        this.isIssued = true;
        this.issuedTo = studentName;
    }

    public void returnBook() {
        this.isIssued = false;
        this.issuedTo = null;
    }

    @Override
    public String toString() {
        return "Book ID: " + id + ", Title: " + title + ", Author: " + author + ", Issued: " + (isIssued ? "Yes, to " + issuedTo : "No");
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addBook() {
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Book Author: ");
        String author = scanner.nextLine();
        books.add(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    public void searchBooks() {
        System.out.print("Enter search term (ID, Title, Author): ");
        String searchTerm = scanner.nextLine();
        for (Book book : books) {
            if (book.getId().equalsIgnoreCase(searchTerm) || book.getTitle().equalsIgnoreCase(searchTerm) || book.getAuthor().equalsIgnoreCase(searchTerm)) {
                System.out.println(book);
            }
        }
    }

    public void updateBookDetails() {
        System.out.print("Enter Book ID to update: ");
        String id = scanner.nextLine();
        for (Book book : books) {
            if (book.getId().equalsIgnoreCase(id)) {
                System.out.print("Enter new Title: ");
                book.setTitle(scanner.nextLine());
                System.out.print("Enter new Author: ");
                book.setAuthor(scanner.nextLine());
                System.out.println("Book details updated successfully!");
                return;
            }
        }
        System.out.println("Book not found!");
    }

    public void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        String id = scanner.nextLine();
        for (Book book : books) {
            if (book.getId().equalsIgnoreCase(id)) {
                if (!book.isIssued()) {
                    System.out.print("Enter Student Name: ");
                    String studentName = scanner.nextLine();
                    book.issueTo(studentName);
                    System.out.println("Book issued successfully!");
                } else {
                    System.out.println("Book is already issued to " + book.getIssuedTo());
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }

    public void returnBook() {
        System.out.print("Enter Book ID to return: ");
        String id = scanner.nextLine();
        for (Book book : books) {
            if (book.getId().equalsIgnoreCase(id)) {
                if (book.isIssued()) {
                    book.returnBook();
                    System.out.println("Book returned successfully!");
                } else {
                    System.out.println("Book is not issued.");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }

    public void collectFine() {
        System.out.print("Enter Book ID to collect fine: ");
        String id = scanner.nextLine();
        System.out.print("Enter fine amount: ");
        double fine = scanner.nextDouble();
        scanner.nextLine(); // consume the newline
        System.out.println("Collected fine of $" + fine + " for book ID: " + id);
    }

    public void menu() {
        while (true) {
            System.out.println("\nLibrary Management System:");
            System.out.println("1. Insert new Book");
            System.out.println("2. Search for book(s)");
            System.out.println("3. Update book details");
            System.out.println("4. Issue a book to a student");
            System.out.println("5. Return a book from a student");
            System.out.println("6. Collect fine");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    searchBooks();
                    break;
                case 3:
                    updateBookDetails();
                    break;
                case 4:
                    issueBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    collectFine();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

public class library_management {
    public static void main(String[] args) {
        Library library = new Library();
        library.menu();
    }
}