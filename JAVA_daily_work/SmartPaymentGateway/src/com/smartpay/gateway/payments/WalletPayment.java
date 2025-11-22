package com.smartpay.gateway.payments;

import com.smartpay.gateway.exceptions.*;
import com.smartpay.gateway.interfaces.Refundable;

public class WalletPayment extends PaymentMethod implements Refundable {
    private double balance;

    public WalletPayment(String payer, double balance) {
        super(payer);
        this.balance = balance;
    }

    @Override
    public boolean processPayment(double amount) throws InvalidAmountException, InsufficientBalanceException {
        if (amount <= 0) throw new InvalidAmountException("Wallet payment amount must be positive");
        if (amount > balance) throw new InsufficientBalanceException("Wallet balance insufficient: balance=" + balance + ", required=" + amount);
        balance -= amount;
        System.out.println("WalletPayment: Paid Rs." + amount + ". New balance=" + balance);
        return true;
    }

    @Override
    public boolean refund(double amount) {
        if (amount <= 0) return false;
        balance += amount;
        System.out.println("WalletPayment: Refund success. New balance=" + balance);
        return true;
    }

    public double getBalance() { return balance; }
}
