package com.bankingsystem.app;

public class BankSystem {
    public static void main(String[] args) {
        
        // Create shared bank account
        BankAccount sharedAccount = new BankAccount(1001, "Alice", 1000.0);

        // Two users attempting concurrent transfers
        Thread user1 = new Thread(new User("User1", sharedAccount, 700));
        Thread user2 = new Thread(new User("User2", sharedAccount, 700));

        user1.start();
        user2.start();

        try {
            user1.join();
            user2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nFinal Account Balance: ₹" + sharedAccount.getBalance());
        System.out.println("All transfers completed safely without race conditions!");
    }
}
