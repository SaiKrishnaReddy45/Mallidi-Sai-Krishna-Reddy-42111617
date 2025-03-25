package credentials;
import java.sql.*;
import java.util.Scanner;

class Librarian {
    public static boolean login(Connection conn, String username, String password) {
        String query = "SELECT * FROM employee WHERE username = ? AND password = ?";
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
            System.out.println("\nLibrarian Menu:");
            System.out.println("1. View all books");
            System.out.println("2. Check book availability");
            System.out.println("3. Add new book");
            System.out.println("4. BookCount");
            System.out.println("5. Issue book");
            System.out.println("6. Collect book");
            System.out.println("7. Logout");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAllBooks(conn);
                    break;
                case 2:
                    System.out.print("Enter book ID: ");
                    int checkId = scanner.nextInt();
                    scanner.nextLine();
                    boolean available = checkBookAvailability(conn, checkId);
                    System.out.println("Book availability: " + (available ? "Available" : "Not Available"));
                    break;
                case 3:
                    addNewBook(conn, scanner);
                    break;
                case 4:
                    System.out.print("Enter book ID to check count: ");
                    int Id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(bookCount(conn, Id)); 
                    break;
                case 5:
                    System.out.print("Enter book ID to issue: ");
                    int issueId = scanner.nextInt();
                    scanner.nextLine();
                    issueBook(conn, issueId);
                    break;
                case 6:
                    System.out.print("Enter book ID to collect: ");
                    int collectId = scanner.nextInt();
                    scanner.nextLine();
                    collectBook(conn, collectId);
                    break;
                case 7:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public void viewAllBooks(Connection conn) {
        String query = "SELECT * FROM book";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("book_id");
                String title = rs.getString("book_name");
                String author = rs.getString("author");
                int bookCount = rs.getInt("book_count");
                boolean isAvailable = bookCount > 0;
                System.out.println("Id: " + id + ", Title: " + title + ", Author: " + author + ", Count: " + bookCount + ", Available: " + (isAvailable ? "Yes" : "No"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int bookCount(Connection conn, int id) {
        String query = "SELECT book_count FROM book WHERE book_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("book_count");
                } else {
                    System.out.println("Book ID not found.");
                    return -1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public boolean checkBookAvailability(Connection conn, int id) {
        String query = "SELECT book_count FROM book WHERE book_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("book_count") > 0;
            } else {
                System.out.println("Book ID not found.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void addNewBook(Connection conn, Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter book count: ");
        int count = scanner.nextInt();
        scanner.nextLine();
        boolean isAvailable = count > 0;

        String query = "INSERT INTO book (book_name, author, genre, book_count, is_available) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, genre);
            pstmt.setInt(4, count);
            pstmt.setBoolean(5, isAvailable);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Book added successfully!" : "Failed to add book!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void issueBook(Connection conn, int id) {
        String query = "UPDATE book SET book_count = book_count - 1, is_available = CASE WHEN book_count - 1 > 0 THEN true ELSE false END WHERE book_id = ? AND book_count > 0";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Book issued successfully!" : "Failed to issue book! It might not be available.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void collectBook(Connection conn, int id) {
        String query = "UPDATE book SET book_count = book_count + 1, is_available = true WHERE book_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Book collected successfully!" : "Failed to collect book!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
