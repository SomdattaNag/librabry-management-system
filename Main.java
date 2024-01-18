import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private int serialNumber;

    public Book(String title, String author, int serialNumber) {
        this.title = title;
        this.author = author;
        this.serialNumber = serialNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    @Override
    public String toString() {
        return "Serial Number: " + serialNumber + "\nTitle: " + title + "\nAuthor: " + author + "\n";
    }
}

class LibrarySystem {
    private ArrayList<Book> books;

    public LibrarySystem() {
        this.books = new ArrayList<>();
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", 1));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", 2));
        books.add(new Book("1984", "George Orwell", 3));
        books.add(new Book("Pride and Prejudice", "Jane Austen", 4));
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 5));
    }

    public void addBook(String title, String author, int serialNumber) {
        Book newBook = new Book(title, author, serialNumber);
        books.add(newBook);
        System.out.println("Book added successfully!");
    }

    public void searchBook(int serialNumber) {
        for (Book book : books) {
            if (book.getSerialNumber() == serialNumber) {
                System.out.println("Book found:\n" + book);
                return;
            }
        }
        System.out.println("Book not found with serial number: " + serialNumber);
    }

    public void deleteBook(int serialNumber) {
        for (Book book : books) {
            if (book.getSerialNumber() == serialNumber) {
                books.remove(book);
                System.out.println("Book deleted successfully!");
                return;
            }
        }
        System.out.println("Book not found with serial number: " + serialNumber);
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("Library Books:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        LibrarySystem librarySystem = new LibrarySystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary System Options:");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Display Books");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.next();
                    System.out.print("Enter book author: ");
                    String author = scanner.next();
                    System.out.print("Enter book serial number: ");
                    int serialNumber = scanner.nextInt();
                    librarySystem.addBook(title, author, serialNumber);
                    break;
                case 2:
                    System.out.print("Enter serial number to search: ");
                    int searchSerialNumber = scanner.nextInt();
                    librarySystem.searchBook(searchSerialNumber);
                    break;
                case 3:
                    System.out.print("Enter serial number to delete: ");
                    int deleteSerialNumber = scanner.nextInt();
                    librarySystem.deleteBook(deleteSerialNumber);
                    break;
                case 4:
                    librarySystem.displayBooks();
                    break;
                case 5:
                    System.out.println("Exiting the library system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
