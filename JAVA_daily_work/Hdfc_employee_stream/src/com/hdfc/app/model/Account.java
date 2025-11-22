package com.hdfc.app.model;

public class Account {
    private final String accountNumber;
    private double balance;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void deposit(double amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount must be >= 0");
        balance += amount;
    }

    public synchronized boolean withdraw(double amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount must be >= 0");
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
