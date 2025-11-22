package com.bank.app;

public class FundTransferTask implements Runnable {
    private final Bank bank;
    private final Transaction transaction;

    public FundTransferTask(Bank bank, Transaction transaction) {
        this.bank = bank;
        this.transaction = transaction;
    }

    @Override
    public void run() {
        boolean success = bank.transferFunds(
            transaction.getFromAccount(),
            transaction.getToAccount(),
            transaction.getAmount()
        );
        if (success) {
            System.out.println("Transferred " + transaction.getAmount() +
                " from account " + transaction.getFromAccount() +
                " to account " + transaction.getToAccount());
        }
    }
}
