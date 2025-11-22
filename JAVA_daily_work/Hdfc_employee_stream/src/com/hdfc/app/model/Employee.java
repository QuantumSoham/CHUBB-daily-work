package com.hdfc.app.model;

public class Employee {
    private String name;
    private double salary;
    private Account account;

    public Employee(String name, double salary, Account account) {
        this.name = name;
        this.salary = salary;
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Account getAccount() {
        return account;
    }
}
