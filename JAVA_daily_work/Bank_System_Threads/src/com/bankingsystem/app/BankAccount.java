package com.bankingsystem.app;

public class BankAccount {
    private int accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(int accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    // Thread-safe transfer method
    public synchronized void transfer(String user, double amount) {
        System.out.println(user + " is attempting to transfer ₹" + amount);

        if (balance >= amount) {
            System.out.println(user + " verified balance: ₹" + balance);
            try {
                Thread.sleep(1000); // simulate processing delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            balance -= amount;
            System.out.println(user + " completed transfer of ₹" + amount + ". Remaining balance: ₹" + balance);
        } else {
            System.out.println(user + " attempted to transfer ₹" + amount + " but insufficient balance! (Current: ₹" + balance + ")");
        }
    }

    public synchronized double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }
}
