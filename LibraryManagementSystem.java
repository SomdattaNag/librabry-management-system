import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    }

    public String searchBook(int serialNumber) {
        for (Book book : books) {
            if (book.getSerialNumber() == serialNumber) {
                return book.toString();
            }
        }
        return "Book not found with serial number: " + serialNumber;
    }

    public String deleteBook(int serialNumber) {
        for (Book book : books) {
            if (book.getSerialNumber() == serialNumber) {
                books.remove(book);
                return "Book deleted successfully!";
            }
        }
        return "Book not found with serial number: " + serialNumber;
    }

    public String displayBooks() {
        if (books.isEmpty()) {
            return "No books in the library.";
        } else {
            StringBuilder sb = new StringBuilder("Library Books:\n");
            for (Book book : books) {
                sb.append(book).append("\n");
            }
            return sb.toString();
        }
    }
}

public class LibraryManagementSystem{
    private LibrarySystem librarySystem;
    private JFrame frame;
    private JTextArea displayArea;
    private JTextField titleField, authorField, serialNumberField;

    public LibraryManagementSystem() {
        librarySystem = new LibrarySystem();

        frame = new JFrame("Library System");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // Setting a background color
        frame.getContentPane().setBackground(new Color(245, 245, 245));

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        // Title Field
        panel.add(new JLabel("Title: "), gbc);
        titleField = new JTextField(15);
        titleField.setPreferredSize(new Dimension(200, 30));
        titleField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        gbc.gridx = 1;
        panel.add(titleField, gbc);

        // Author Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Author: "), gbc);
        authorField = new JTextField(15);
        authorField.setPreferredSize(new Dimension(200, 30));
        authorField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        gbc.gridx = 1;
        panel.add(authorField, gbc);

        // Serial Number Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Serial Number: "), gbc);
        serialNumberField = new JTextField(15);
        serialNumberField.setPreferredSize(new Dimension(200, 30));
        serialNumberField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        gbc.gridx = 1;
        panel.add(serialNumberField, gbc);

        // Buttons
        JButton addButton = new JButton("Add Book");
        JButton searchButton = new JButton("Search Book");
        JButton deleteButton = new JButton("Delete Book");
        JButton displayButton = new JButton("Display Books");

        // Button styling
        Font buttonFont = new Font("Arial", Font.BOLD, 12);
        addButton.setFont(buttonFont);
        searchButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);
        displayButton.setFont(buttonFont);

        addButton.setBackground(new Color(64, 158, 255));
        addButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(64, 158, 255));
        searchButton.setForeground(Color.WHITE);
        deleteButton.setBackground(new Color(255, 97, 97));
        deleteButton.setForeground(Color.WHITE);
        displayButton.setBackground(new Color(0, 204, 102));
        displayButton.setForeground(Color.WHITE);

        // Adding buttons to the panel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(addButton, gbc);

        gbc.gridy = 4;
        panel.add(searchButton, gbc);

        gbc.gridy = 5;
        panel.add(deleteButton, gbc);

        gbc.gridy = 6;
        panel.add(displayButton, gbc);

        // Display area for results
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        displayArea.setBackground(new Color(240, 240, 240));
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Add components to the frame
        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Button actions
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                int serialNumber = Integer.parseInt(serialNumberField.getText());
                librarySystem.addBook(title, author, serialNumber);
                displayArea.setText("Book added successfully!");
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int serialNumber = Integer.parseInt(serialNumberField.getText());
                String result = librarySystem.searchBook(serialNumber);
                displayArea.setText(result);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int serialNumber = Integer.parseInt(serialNumberField.getText());
                String result = librarySystem.deleteBook(serialNumber);
                displayArea.setText(result);
            }
        });

        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String result = librarySystem.displayBooks();
                displayArea.setText(result);
            }
        });

        // Show the frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new LibraryManagementSystem();
    }
}
