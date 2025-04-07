package project;

import java.util.Scanner;
import java.util.HashMap;

class Account {
    private String accountNumber;
    private String pin;
    private double balance;
    
    public Account(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }
    
    public boolean validatePin(String enteredPin) {
        return pin.equals(enteredPin);
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        if(amount > 0) balance += amount;
    }
    
    public void withdraw(double amount) {
        if(amount > 0 && amount <= balance) balance -= amount;
    }
    
    public void transfer(Account recipient, double amount) {
        if(amount > 0 && balance >= amount) {
            balance -= amount;
            recipient.balance += amount;
        }
    }
}

public class Atm_Interface {
    private static HashMap<String, Account> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    
    static {
        // Initialize test accounts
        accounts.put("123456", new Account("123456", "1111", 5000.0));
        accounts.put("654321", new Account("654321", "2222", 3000.0));
    }
    
    public static void main(String[] args) {
        System.out.println("=== ATM SIMULATION SYSTEM ===");
        
        Account currentAccount = authenticateUser();
        if(currentAccount != null) {
            showMainMenu(currentAccount);
        }
    }
    
    private static Account authenticateUser() {
        int attempts = 0;
        while(attempts < 3) {
            System.out.print("Enter account number: ");
            String accountNumber = scanner.nextLine();
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();
            
            Account account = accounts.get(accountNumber);
            if(account != null && account.validatePin(pin)) {
                return account;
            }
            System.out.println("Invalid credentials. Attempts left: " + (2 - attempts));
            attempts++;
        }
        System.out.println("Too many failed attempts. Exiting...");
        return null;
    }
    
    private static void showMainMenu(Account account) {
        while(true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Select option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline
            
            switch(choice) {
                case 1:
                    System.out.printf("Current balance: $%.2f%n", account.getBalance());
                    break;
                case 2:
                    processDeposit(account);
                    break;
                case 3:
                    processWithdrawal(account);
                    break;
                case 4:
                    processTransfer(account);
                    break;
                case 5:
                    System.out.println("Thank you for using our ATM!");
                    return;
                default:
                    System.out.println("Invalid selection");
            }
        }
    }
    
    private static void processDeposit(Account account) {
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        account.deposit(amount);
        System.out.printf("Deposited $%.2f. New balance: $%.2f%n", amount, account.getBalance());
    }
    
    private static void processWithdrawal(Account account) {
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        if(amount <= account.getBalance()) {
            account.withdraw(amount);
            System.out.printf("Withdrawn $%.2f. New balance: $%.2f%n", amount, account.getBalance());
        } else {
            System.out.println("Insufficient funds");
        }
    }
    
    private static void processTransfer(Account sender) {
        System.out.print("Enter recipient account number: ");
        String recipientNumber = scanner.nextLine();
        Account recipient = accounts.get(recipientNumber);
        
        if(recipient != null) {
            System.out.print("Enter transfer amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            sender.transfer(recipient, amount);
            System.out.printf("Transferred $%.2f to %s%n", amount, recipientNumber);
        } else {
            System.out.println("Invalid recipient account");
        }
    }
}