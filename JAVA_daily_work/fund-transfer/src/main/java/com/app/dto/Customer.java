package com.app.dto;

public class Customer {
    private String name;
    private String email;
    private String accountNumber;
    private double amountbalance;

    public Customer(String name, String email, String accountNumber,int amountbalance) {
        this.name = name;
        this.email = email;
        this.accountNumber = accountNumber;
        this.amountbalance = amountbalance;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getAccountNumber() { return accountNumber; }
    public double getAmountbalance() { return amountbalance; }

    public void setAmountbalance(double amountbalance) {
        this.amountbalance = amountbalance;
    }
}
