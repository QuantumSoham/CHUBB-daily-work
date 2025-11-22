package com.app.model;

public class EmployeeAccount {
    private String employeeName;
    private String accountNumber;
    private double balance;

    public EmployeeAccount(String employeeName, String accountNumber) {
        this.employeeName = employeeName;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public synchronized void credit(double amount) {
        balance += amount;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return employeeName + " | Acc: " + accountNumber + " | Balance: " + balance;
    }
}
