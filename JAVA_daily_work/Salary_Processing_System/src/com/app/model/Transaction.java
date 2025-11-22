package com.app.model;

public class Transaction {
    private String transactionId;
    private String employeeName;
    private String accountNumber;
    private double amount;

    public Transaction(String transactionId, String employeeName, String accountNumber, double amount) {
        this.transactionId = transactionId;
        this.employeeName = employeeName;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return transactionId + " | " + employeeName + " | " + accountNumber + " | " + amount;
    }
}
