import java.util.*;

public class DigitalLibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("==================================");
            System.out.println("Digital Library Management System");
            System.out.println("==================================");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    adminLogin(library, scanner);
                    break;
                case 2:
                    userLogin(library, scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void adminLogin(Library library, Scanner scanner) {
        // Implement admin login logic
        // For simplicity, let's assume a successful login
        System.out.println("Admin login successful!");

        while (true) {
            System.out.println("==================================");
            System.out.println("Admin Menu");
            System.out.println("==================================");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Display All Books");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");
            int adminChoice = scanner.nextInt();

            switch (adminChoice) {
                case 1:
                    library.addBook(scanner);
                    break;
                case 2:
                    library.updateBook(scanner);
                    break;
                case 3:
                    library.deleteBook(scanner);
                    break;
                case 4:
                    library.displayAllBooks();
                    break;
                case 5:
                    System.out.println("Logging out from Admin account.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void userLogin(Library library, Scanner scanner) {
        // Implement user login logic
        // For simplicity, let's assume a successful login
        System.out.println("User login successful!");

        while (true) {
            System.out.println("==================================");
            System.out.println("User Menu");
            System.out.println("==================================");
            System.out.println("1. Display All Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    library.displayAllBooks();
                    break;
                case 2:
                    library.borrowBook(scanner);
                    break;
                case 3:
                    library.returnBook(scanner);
                    break;
                case 4:
                    System.out.println("Logging out from User account.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static class Library {
        private List<Book> books = new ArrayList<>();

        public void addBook(Scanner scanner) {
            // Implement add book logic
            // For simplicity, let's add a book with user input
            System.out.print("Enter Book Title: ");
            String title = scanner.nextLine();

            System.out.print("Enter Author: ");
            String author = scanner.nextLine();

            Book book = new Book(title, author);
            books.add(book);

            System.out.println("Book added successfully!");
        }

        public void updateBook(Scanner scanner) {
            // Implement update book logic
            // For simplicity, let's update a book with user input
            System.out.print("Enter Book Title to update: ");
            String title = scanner.nextLine();

            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    System.out.print("Enter new Author: ");
                    String newAuthor = scanner.nextLine();
                    book.setAuthor(newAuthor);
                    System.out.println("Book updated successfully!");
                    return;
                }
            }

            System.out.println("Book not found with title: " + title);
        }

        public void deleteBook(Scanner scanner) {
            // Implement delete book logic
            // For simplicity, let's delete a book with user input
            System.out.print("Enter Book Title to delete: ");
            String title = scanner.nextLine();

            books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));

            System.out.println("Book deleted successfully!");
        }

        public void displayAllBooks() {
            System.out.println("Books in the Library:");
            for (Book book : books) {
                System.out.println(book);
            }
        }

        public void borrowBook(Scanner scanner) {
            // Implement borrow book logic
            // For simplicity, let's remove a book when borrowed
            System.out.print("Enter Book Title to borrow: ");
            String title = scanner.nextLine();

            books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));

            System.out.println("Book borrowed successfully!");
        }

        public void returnBook(Scanner scanner) {
            // Implement return book logic
            // For simplicity, let's add a book when returned
            System.out.print("Enter Book Title to return: ");
            String title = scanner.nextLine();

            Book returnedBook = new Book(title, "Unknown Author");
            books.add(returnedBook);

            System.out.println("Book returned successfully!");
        }
    }

    static class Book {
        private String title;
        private String author;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        @Override
        public String toString() {
            return "Title: " + title + ", Author: " + author;
        }
    }
}
