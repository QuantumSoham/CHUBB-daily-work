package com.payment.process;

import java.util.*;
import com.payment.dto.Account;

public class AccountOperations {

    public static void main(String[] args) {
        AccountOperations obj = new AccountOperations();
        obj.processAccounts();
    }

    public void processAccounts() {
        List<Account> accounts = new ArrayList<>();

        accounts.add(new Account("Amit", "ACC102", "T100", "India", "IFSC001", 7500.50));
        accounts.add(new Account("Raj", "ACC101", "T200", "USA", "IFSC002", 6500.00));
        accounts.add(new Account("Sneha", "ACC105", "T300", "UK", "IFSC003", 12000.00));
        accounts.add(new Account("Ravi", "ACC103", "T400", "India", "IFSC004", 8500.00));
        accounts.add(new Account("Amit", "ACC102", "T100", "India", "IFSC001", 7500.50)); // duplicate

        System.out.println("----- Unique Accounts via HashSet (equals + hashCode) -----");
        Set<Account> uniqueAccounts = new HashSet<>(accounts);
        for (Account acc : uniqueAccounts)
            System.out.println(acc);

        // Sorting by account number
        System.out.println("\n----- Sorted by Account Number -----");
        Collections.sort(accounts, new AccountNumberComparator());
        accounts.forEach(System.out::println);

        // Sorting by balance
        System.out.println("\n----- Sorted by Balance -----");
        Collections.sort(accounts, new AccountBalanceComparator());
        accounts.forEach(System.out::println);

        // Sorting by account holder name (Comparable)
        System.out.println("\n----- Sorted by Account Holder Name (Comparable) -----");
        Collections.sort(accounts); // uses compareTo
        accounts.forEach(System.out::println);
    }
}
