package com.app.interfaces;

import com.app.dto.Account;

public interface Transferable {
    boolean fundTransfer(Account from, Account to, double amount);
}
