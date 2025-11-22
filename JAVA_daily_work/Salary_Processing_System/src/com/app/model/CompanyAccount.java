package com.app.model;

public class CompanyAccount {
    private double balance;

    public CompanyAccount(double balance) {
        this.balance = balance;
    }

    public synchronized boolean transferSalary(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }
}
