import java.util.*;
public class SavingAccount extends BankAccount{
    private List<Transactions> transactionHistory;
    public SavingAccount(int accountNumber,String accontHolderName,double balance){
        super(accountNumber, accontHolderName, balance);
        transactionHistory=new ArrayList<>();
    }
    public void deposit(double amount){
        double newBalance=getBalance()+amount;
        setBalance(newBalance);
        transactionHistory.add(new Transactions("Deposit ", amount));
        System.out.println("\nAmount deposited: "+amount+"\nCurrent Balance: "+getBalance());
    }
    public void withdraw(double amount){
        if(getBalance()>amount){
            double newBalance=getBalance()-amount;
            setBalance(newBalance);
            transactionHistory.add(new Transactions("withdraw", amount));
            System.out.println("\nAmount withdrawn: "+amount+"\nCurrent Balance: "+getBalance());
        }else{
            System.out.println("Insufficient balance");
        }
    }
    public void applyIntrest(double interest){
        double interestval=getBalance()*(interest/100);
        double newBalance=getBalance() +interestval;
        setBalance(newBalance);
        transactionHistory.add(new Transactions("Interest", interestval));
        System.out.println("Interest applied: " + interestval + ", New Balance: " + getBalance());

    }
    public void transferMoney(double amount){
        if(getBalance()>amount){
            withdraw(amount);
            transactionHistory.add(new Transactions("Transfer", amount));
        }else{
            System.out.println("Cannot Transfer Money");
        }
    }

    public void displayTransactionHistory(){
        if(transactionHistory.isEmpty()){
            System.out.println("There are no transactions yet");
        }else{
            System.out.println("----------------------------------Transaction History----------------------------------------");
            for(Transactions t:transactionHistory){
                System.out.println(t);
            }
        }
    }
}
