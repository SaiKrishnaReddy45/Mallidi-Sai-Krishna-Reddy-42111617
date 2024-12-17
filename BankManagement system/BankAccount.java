public abstract class BankAccount{
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    
    public BankAccount(int accountNumber,String accontHolderName,double balance){
        this.accountNumber=accountNumber;
        this.accountHolderName=accontHolderName;
        this.balance=balance;
    }
    public abstract void withdraw(double amount);
    public abstract void deposit(double amount);

    public double getBalance(){
        return balance;
    }
    public void setBalance(double amount){
        this.balance=amount;
    }
    public void checkBalance(){
        System.out.println("customer name: "+accountHolderName+"\n"+"Account number: "+accountNumber);
        System.out.println("\nThe current balance is: "+balance);
    }
    public int getAccountNumber(){
        return accountNumber;
    }

    public String getAccountHolderName(){
        return accountHolderName;
    }
}