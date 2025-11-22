package com.bank.features;

import com.bank.model.Account;
import java.util.Comparator;

public class SortByNameDesc implements Comparator<Account> {
    @Override
    public int compare(Account a1, Account a2) {
        return a2.getName().compareTo(a1.getName()); // Descending order
    }
}
