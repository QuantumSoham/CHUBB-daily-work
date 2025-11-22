package com.bankingsystem.app;

public class User implements Runnable {
    private String userName;
    private BankAccount account;
    private double transferAmount;

    public User(String userName, BankAccount account, double transferAmount) {
        this.userName = userName;
        this.account = account;
        this.transferAmount = transferAmount;
    }

    @Override
    public void run() {
        account.transfer(userName, transferAmount);
    }
}
