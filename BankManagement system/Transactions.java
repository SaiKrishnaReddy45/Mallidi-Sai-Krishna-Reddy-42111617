import java.util.Date;
public class Transactions {
    private String accountType;
    private double amount;
    private Date date;
    Transactions(String accountType,double amount){
        this.accountType=accountType;
        this.amount=amount;
        this.date=new Date();
    }
    public String getType(){
        return accountType;
    }
    public double getAmount(){
        return amount;
    }
    public Date getDate(){
        return date;
    }

    public String toString(){
        return "Transaction type: "+accountType+", Amount: "+amount+", Date: "+date;
    }
}
