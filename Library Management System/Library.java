import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added.");
    }

    public void removeBook(int id) {
        books.removeIf(b -> b.getId() == id);
        System.out.println("Book removed.");
    }

    public void listBooks() {
        System.out.println("List of Books:");
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            books.forEach(System.out::println);
        }
    }

    public void listAvailableBooks() {
        System.out.println("Available Books:");
        boolean anyAvailable = false;
        for (Book book : books) {
            if (!book.isIssued()) {
                System.out.println(book);
                anyAvailable = true;
            }
        }
        if (!anyAvailable) {
            System.out.println("No available books.");
        }
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added.");
    }

    public void issueBook(int bookId, int studentId) {
        Book book = findBook(bookId);
        if (book != null && !book.isIssued()) {
            book.setIssued(true);
            System.out.println("Book issued to Student ID: " + studentId);
        } else {
            System.out.println("Book not available.");
        }
    }

    public void returnBook(int bookId) {
        Book book = findBook(bookId);
        if (book != null && book.isIssued()) {
            book.setIssued(false);
            System.out.println("Book returned.");
        } else {
            System.out.println("Book not issued.");
        }
    }

    private Book findBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}
