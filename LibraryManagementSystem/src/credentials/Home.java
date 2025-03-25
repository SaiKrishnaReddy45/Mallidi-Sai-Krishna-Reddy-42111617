package credentials;
import java.sql.*;
import java.util.Scanner;

class Home {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Choose an option:\n1. Login\n2. Sign Up\n3. Exit");
        int action = scanner.nextInt();
        scanner.nextLine();

        String url = System.getenv("URL");
        String dbUser =System.getenv("USERNAME");
        String dbPassword = System.getenv("PASSWORD");

        try {
            Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
            
            if (action == 1) {
                System.out.println("Login as:\n1. Librarian\n2. Consumer");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                System.out.print("Enter Username: ");
                String username = scanner.nextLine();
                System.out.print("Enter Password: ");
                String password = scanner.nextLine();

                if (choice == 1) {
                    boolean loginSuccess = Librarian.login(conn, username, password);
                    if (loginSuccess) {
                        System.out.println("Login successful!");
                        Librarian librarian = new Librarian();
                        librarian.menu(conn);
                    } else {
                        System.out.println("Invalid credentials!");
                    }
                } else if (choice == 2) {
                    boolean loginSuccess = Consumer.login(conn, username, password);
                    if (loginSuccess) {
                        System.out.println("Login successful!");
                        Consumer consumer = new Consumer();
                        consumer.menu(conn);
                    } else {
                        System.out.println("Invalid credentials!");
                    }
                } else {
                    System.out.println("Invalid choice!");
                }
            } else if (action == 2) {
                System.out.println("Sign up as:\n1. Librarian\n2. Consumer");
                int userType = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Phone Number (10 digits): ");
                String phoneNo = scanner.nextLine();
                System.out.print("Enter Email: ");
                String email = scanner.nextLine();
                System.out.print("Enter Username: ");
                String username = scanner.nextLine();
                System.out.print("Enter Password (min 9 characters): ");
                String password = scanner.nextLine();

                if (password.length() <= 8 || phoneNo.length() != 10) {
                    System.out.println("Invalid input! Ensure password is at least 9 characters and phone number is 10 digits.");
                } else {
                    String query;
                    if (userType == 1) {
                        query = "INSERT INTO employee (Name, PhoneNo, email, userName, password) VALUES (?, ?, ?, ?, ?)";
                    } else if (userType == 2) {
                        query = "INSERT INTO consumer (Name, PhoneNo, email, userName, password) VALUES (?, ?, ?, ?, ?)";
                    } else {
                        System.out.println("Invalid choice!");
                        return;
                    }
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, name);
                        pstmt.setString(2, phoneNo);
                        pstmt.setString(3, email);
                        pstmt.setString(4, username);
                        pstmt.setString(5, password);
                        int rowsAffected = pstmt.executeUpdate();
                        System.out.println(rowsAffected > 0 ? "Sign-up successful!" : "Sign-up failed!");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else if (action ==3){
                System.out.println("Exit!");
                return;
            }else{
                System.out.println("Invalid option!");
            }
            
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        scanner.close();
    }
}
