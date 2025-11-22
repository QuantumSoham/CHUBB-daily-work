package com.payment.process;

import java.util.Comparator;
import com.payment.dto.Account;

public class AccountBalanceComparator implements Comparator<Account> {
    @Override
    public int compare(Account a1, Account a2) {
        return Double.compare(a1.getBalance(), a2.getBalance());
    }
}
