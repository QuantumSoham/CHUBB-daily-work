package com.bank.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();

        // Create 5 accounts with initial balance
        for (int i = 1; i <= 5; i++) {
            bank.addAccount(new Account(i, 1000));
        }

        ExecutorService executor = Executors.newFixedThreadPool(10); // 10 concurrent users
        Random random = new Random();

        // Simulate 20 concurrent fund transfers
        for (int i = 0; i < 20; i++) {
            int from = random.nextInt(5) + 1;
            int to;
            do {
                to = random.nextInt(5) + 1;
            } while (to == from);

            double amount = random.nextInt(500) + 1;

            Transaction transaction = new Transaction(from, to, amount);
            executor.submit(new FundTransferTask(bank, transaction));
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // wait for all tasks to finish
        }

        System.out.println("\nFinal account balances:");
        bank.printAllAccounts();
    }
}
