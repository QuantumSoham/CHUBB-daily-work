package com.bank.features;

import com.bank.model.*;
import java.util.*;
import java.util.stream.*;

public class PaymentsOps {

    // ✅ Fund Transfer Method
    public boolean fundTransfer(Account from, Account to, double amount) {
        if (from.withdraw(amount)) {
            to.deposit(amount);
            System.out.println("✅ Transfer Successful: ₹" + amount + " from " + from.getName() + " to " + to.getName());
            return true;
        } else {
            System.out.println("❌ Transfer Failed: Insufficient balance in " + from.getName() + "'s account");
            return false;
        }
    }

    public static void main(String[] args) {
        PaymentsOps ops = new PaymentsOps();

        // 🔹 Create sample accounts
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("1003", "Ravi", 65000));
        accounts.add(new Account("1001", "Soham", 90000));
        accounts.add(new Account("1005", "Priya", 50000));
        accounts.add(new Account("1002", "Amit", 85000));
        accounts.add(new Account("1004", "John", 30000));

        System.out.println("Original Account List:");
        accounts.forEach(System.out::println);

        // ✅ Perform a fund transfer
        ops.fundTransfer(accounts.get(1), accounts.get(4), 10000);

        // 🔹 Sort using Stream.sorted() - by balance (ascending)
        
        accounts.stream()
                .sorted(Comparator.comparing(Account::getBalance))
                .forEach(System.out::println);

        // 🔹 Sort by balance (descending)
        
        accounts.stream()
                .sorted(Comparator.comparing(Account::getBalance).reversed())
                .forEach(System.out::println);

        // 🔹 Sort by account number
        
        accounts.stream()
                .sorted(Comparator.comparing(Account::getAccountNo))
                .forEach(System.out::println);

        // 🔹 Sort by name alphabetically
        
        accounts.stream()
                .sorted(Comparator.comparing(Account::getName))
                .forEach(System.out::println);

        // 🔹 Multi-level sort: by balance, then by name
        
        accounts.stream()
                .sorted(Comparator.comparing(Account::getBalance)
                        .thenComparing(Account::getName))
                .forEach(System.out::println);

        // 🔹 Using custom comparator class
        
        accounts.stream()
                .sorted(new SortByNameDesc())
                .forEach(System.out::println);
    }
}
