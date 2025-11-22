package com.app.dto;

public class Customer {
    private String name;
    private String country;
    private String accountNo;
    private String ifsc;
    private double balance;

    public Customer(String name, String country, String accountNo, String ifsc, double balance) {
        this.name = name;
        this.country = country;
        this.accountNo = accountNo;
        this.ifsc = ifsc;
        this.balance = balance;
    }

    public String getName() { return name; }
    public String getCountry() { return country; }
    public String getAccountNo() { return accountNo; }
    public String getIfsc() { return ifsc; }
    public double getBalance() { return balance; }

    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public int hashCode() {
        return accountNo.hashCode() + name.toLowerCase().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer c = (Customer) o;
        return this.accountNo.equals(c.accountNo) &&
               this.name.equalsIgnoreCase(c.name);
    }

    @Override
    public String toString() {
        return name + " (" + accountNo + ") Balance: " + balance;
    }
}
