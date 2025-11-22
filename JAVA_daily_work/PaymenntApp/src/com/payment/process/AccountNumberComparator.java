package com.payment.process;

import java.util.Comparator;
import com.payment.dto.Account;

public class AccountNumberComparator implements Comparator<Account> {
    @Override
    public int compare(Account a1, Account a2) {
        return a1.getAccountno().compareTo(a2.getAccountno());
    }
}
