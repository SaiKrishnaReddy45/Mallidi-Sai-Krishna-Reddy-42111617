import java.util.*;
public class Operations extends SavingAccount{
    Operations(int number,String name,double balnce){
        super(number, name, balnce);
    }
    public void details(Scanner sc){
        System.out.println("\nMenu:\n1. Check Balance\n2. Deposit Money\n3. Withdraw Money\n4. Apply Interest\n5. Transfer Money\n6. View Transaction\n7. Exit");
        System.out.print("Enter your choice: ");
        int choice=sc.nextInt();
        switch (choice) {
            case 1:
                checkBalance();
                break;
            case 2:
                System.out.println("Enter amount you want to deposit: ");
                double depositAmount=sc.nextDouble();
                deposit(depositAmount);
                break;
            case 3:
                System.out.println("Enter amount you want to withdraw: ");
                double withdrawAmount=sc.nextDouble();
                withdraw(withdrawAmount);
                break;
            case 4:
               System.out.println("Enter Interest Rate: ");
                float interest=sc.nextFloat();
               applyIntrest(interest);
               break;
            case 5:
               System.out.println("Enter money you want to transfer: ");
               double transferAmount=sc.nextDouble();
               transferMoney(transferAmount); 
               break;              
            case 6:
                displayTransactionHistory();
                break;
            case 7:
                System.out.println("Thank you");
                return;
            default:
              System.out.println("Invalid choice");
                break;
        }
    }
}
