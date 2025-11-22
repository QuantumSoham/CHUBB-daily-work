package com.bank.model;

public class Beneficiary {
    private String name;
    private String accountNo;

    public Beneficiary(String name, String accountNo) {
        this.name = name;
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public String getAccountNo() {
        return accountNo;
    }

    @Override
    public String toString() {
        return name + " (" + accountNo + ")";
    }
}
