package com.hdfc.app.model;

import java.util.List;

public class Company {
    private String name;
    private Account hdfcAccount;  // Company's account in HDFC Bank
    private List<Employee> employees;

    public Company(String name, Account hdfcAccount, List<Employee> employees) {
        this.name = name;
        this.hdfcAccount = hdfcAccount;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public Account getAccount() {
        return hdfcAccount;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
