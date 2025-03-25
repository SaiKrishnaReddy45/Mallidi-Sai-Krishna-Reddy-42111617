package credentials;
import java.sql.*;
import java.util.*;

public class Consumer {
    Librarian librarian = new Librarian();

    public static boolean login(Connection conn, String username, String password) {
        String query = "SELECT * FROM consumer WHERE username = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void menu(Connection conn) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nConsumer Menu:");
            System.out.println("1. View all books");
            System.out.println("2. Request Book");
            System.out.println("3. Return Book");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    librarian.viewAllBooks(conn);
                    break;
                case 2:
                    requestBook(conn, scanner);
                    break;
                case 3:
                    returnBook(conn, scanner);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public void requestBook(Connection conn, Scanner scanner) {
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean isAvailable = librarian.checkBookAvailability(conn, id);
        if (isAvailable) {
            librarian.issueBook(conn, id);
        } else {
            System.out.println("Book is not available.");
        }
    }

    public void returnBook(Connection conn, Scanner scanner) {
        System.out.print("Enter book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        librarian.collectBook(conn, id);
    }
}
