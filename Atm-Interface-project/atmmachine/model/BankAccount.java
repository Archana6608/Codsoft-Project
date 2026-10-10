package atmmachine.model;

public class BankAccount {
    private final String accountNumber;
    private final String accountHolderName;
    private final String securePin;
    private double balance;

    public BankAccount(String accountNumber, String accountHolderName, String securePin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.securePin = securePin;
        this.balance = initialBalance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public double getBalance() { return balance; }

    public boolean authenticate(String pin) {
        return this.securePin.equals(pin);
    }

    public void debit(double amount) { this.balance -= amount; }
    public void credit(double amount) { this.balance += amount; }
}