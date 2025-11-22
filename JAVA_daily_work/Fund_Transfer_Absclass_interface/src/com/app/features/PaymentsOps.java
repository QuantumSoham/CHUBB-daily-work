package com.app.features;

import com.app.dto.*;
import com.app.interfaces.Transferable;

public class PaymentsOps implements Transferable {

    @Override
    public boolean fundTransfer(Account from, Account to, double amount) {
        System.out.println("\nInitiating transfer of " + amount + " from " + from.getAccountNo() + " to " + to.getAccountNo());

        if (from.withdraw(amount)) {
            to.deposit(amount);
            System.out.println("Transfer successful!");
            return true;
        } else {
            System.out.println("Transfer failed!");
            return false;
        }
    }

    public static void main(String[] args) {
        Account acct1 = new SavingsAccount("ACC1001", 5000);
        Account acct2 = new SavingsAccount("ACC2001", 3000);

        System.out.println("Before Transfer:");
        System.out.println(acct1);
        System.out.println(acct2);

        PaymentsOps ops = new PaymentsOps();
        ops.fundTransfer(acct1, acct2, 1500);

        System.out.println("\nAfter Transfer:");
        System.out.println(acct1);
        System.out.println(acct2);
    }
}
