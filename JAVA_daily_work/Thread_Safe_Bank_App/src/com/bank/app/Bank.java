package com.bank.app;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Bank {
    private final Map<Integer, Account> accounts = new ConcurrentHashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account getAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }

    // Thread-safe transfer
    public boolean transferFunds(int fromAccNo, int toAccNo, double amount) {
        Account from = getAccount(fromAccNo);
        Account to = getAccount(toAccNo);

        if (from == null || to == null) {
            System.out.println("Invalid account");
            return false;
        }

        // Lock ordering to prevent deadlock: always lock lower account number first
        Account firstLock = from.getAccountNumber() < to.getAccountNumber() ? from : to;
        Account secondLock = from.getAccountNumber() < to.getAccountNumber() ? to : from;

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (from.withdraw(amount)) {
                    to.deposit(amount);
                    return true;
                } else {
                    System.out.println("Insufficient balance in account " + fromAccNo);
                    return false;
                }
            }
        }
    }

    public void printAllAccounts() {
        accounts.values().forEach(acc ->
            System.out.println("Account " + acc.getAccountNumber() + ": " + acc.getBalance())
        );
    }
}
