package com.app.dto;

public class NEFTProcessFund {

    // Validate that customer data is non-null and balance > 0
    public boolean validateCustomer(Customer c) {
        return c != null && c.getBalance() > 0 && c.getAccountNo() != null;
    }

    // Actual fund transfer logic
    public void processFund(Customer sender, Customer receiver, double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Transfer amount must be greater than zero.");
        }

        if (sender.getBalance() < amount) {
            throw new Exception("Insufficient balance for sender: " + sender.getName());
        }

        // Perform transfer
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        System.out.println("Transfer of ₹" + amount + " successful from " 
                            + sender.getName() + " to " + receiver.getName());
    }
}
