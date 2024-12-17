import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {
        while (true) {
            System.out.print("\n==Library Management System==");
            System.out.println("\n1. Add Book\n2. Remove Book\n3. List Books\n4. Available Books\n5. Add Student\n6. Issue Book\n7. Return Book\n8. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    library.listBooks();
                    break;
                case 4:
                    library.listAvailableBooks();
                    break;
                case 5:
                    addStudent();
                    break;
                case 6:
                    issueBook();
                    break;
                case 7:
                    returnBook();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    System.out.println("Thank You....");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    
    private static void addBook() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        library.addBook(new Book(id, title, author));
    }

    private static void removeBook() {
        System.out.print("Enter ID to remove: ");
        int id = scanner.nextInt();
        library.removeBook(id);
    }

    private static void addStudent() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        library.addStudent(new Student(id, name));
    }

    private static void issueBook() {
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        library.issueBook(bookId, studentId);
    }

    private static void returnBook() {
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        library.returnBook(bookId);
    }
}
