import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter customer details: ");
        System.out.println("Enter Account Number: ");
        int number=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter customer name: ");
        String name=sc.nextLine();
        System.out.println("Enter Balance: ");
        double balance=sc.nextDouble();
        Operations o=new Operations(number, name, balance);
        boolean opinion=true;
        while(opinion){
            o.details(sc);
            System.out.print("\nDo you want to perform another operation? (true/false): ");
            opinion = sc.nextBoolean();
        }
        sc.close();
    }
}
