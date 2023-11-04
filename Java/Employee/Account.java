package Employee;
// Account.java
//package Week5;

public class Account {
    private int acctNo; 
    private int balance;
    public Account(int no, int bal) { 
        acctNo = no; 
        if(bal > 0) 
            balance = bal; 
        else
            balance = 0;   
    } 
    public int getAcctNo() { return acctNo; } 
    public int getBalance() { return balance; } 
    public void deposit() { balance += 100; }
    public void deposit(int amt) {
        if(amt <=0) return; 
        balance += amt; 
    }
    public void withdraw(int amt) {
        if(amt <=0 || amt > balance) return; 
        balance -= amt; 
    } 
    public void transfer(Account acct, int amt) {
        if(amt <=0 || amt > balance) return; 
        balance -= amt; 
        acct.balance += amt;
    }
    //this method is 
    public int compareTo(Account other) {
        int result = 0;
        if(this.balance > other.balance){
            result = 1;
        }
        else if(this.balance < other.balance){
            result = -1;
        }
        else if(this.balance == other.balance){
            result = 0;
        }
        return result;
    }
    //overriding toString() method
    @Override
    public String toString() {
        return String.format("Account %d: $%d", acctNo, balance);
    }
    public static void main(String[] args) {
        Account acct1 = new Account(1234, 10000); 
        Account acct2 = new Account(2345, 20000); 
        // System.out.printf("Account %d: $%d\n", acct1.getAcctNo(), acct1.getBalance()+"\n");
        // System.out.printf("Account %d: $%d\n", acct2.getAcctNo(), acct2.getBalance());
        System.out.println(acct1.toString());
        System.out.println(acct2.toString());
        acct1.deposit(5000);
        acct2.withdraw(25000);
        // System.out.printf("Account %d: $%d\n", acct1.getAcctNo(), acct1.getBalance());
        // System.out.printf("Account %d: $%d\n", acct2.getAcctNo(), acct2.getBalance());
        System.out.println(acct1.toString());
        System.out.println(acct2.toString());
        acct1.transfer(acct2, 5000);
        acct2.withdraw(5000);
        // System.out.printf("Account %d: $%d\n", acct1.getAcctNo(), acct1.getBalance());
        // System.out.printf("Account %d: $%d\n", acct2.getAcctNo(), acct2.getBalance());
        System.out.println(acct1.toString());
        System.out.println(acct2.toString());

        Account acct = new Account(1111, 100); 
        System.out.println("Account No = "+acct.getAcctNo()+", Balance = "+acct.getBalance()); 
        acct.deposit(20); 
        System.out.println("Account No = "+acct.getAcctNo()+", Balance = "+acct.getBalance()); 
        acct.deposit(); // default로 100을 입금한다. 
        System.out.println("Account No = "+acct.getAcctNo()+", Balance = "+acct.getBalance());
        
        //compare balances of each account
        acct1.compareTo(acct1);
        acct2.compareTo(acct2);
    }
}   