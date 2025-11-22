package com.app.dto;

public class SavingsAccount extends Account {

    private static final double MIN_BALANCE = 1000;

    public SavingsAccount(String accountNo, double balance) {
        super(accountNo, balance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited " + amount + " to " + getAccountNo());
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance - amount >= MIN_BALANCE) {
            balance -= amount;
            System.out.println("Withdrawn " + amount + " from " + getAccountNo());
            return true;
        } else {
            System.out.println("Insufficient funds in " + getAccountNo());
            return false;
        }
    }
}
