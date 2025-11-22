package com.app.dto;

public abstract class Account {
    private String accountNo;
    protected double balance;

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public double getBalance() {
        return balance;
    }

    // Abstract methods to be implemented by subclasses
    public abstract void deposit(double amount);
    public abstract boolean withdraw(double amount);

    @Override
    public String toString() {
        return "Account{" +
                "accountNo='" + accountNo + '\'' +
                ", balance=" + balance +
                '}';
    }
}
