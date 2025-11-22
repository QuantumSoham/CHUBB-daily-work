package com.app.dto;

public class NEFTProcessFund {

    public boolean validateCustomer(Customer c) {
        return c != null && c.getAccountNumber() != null;
    }

    public void processFund(Customer from, Customer to, int amount) throws Exception {
        if (from.getAmountbalance() < amount) {
            throw new Exception("Insufficient Balance");
        }

        from.setAmountbalance(from.getAmountbalance() - amount);
        to.setAmountbalance(to.getAmountbalance() + amount);

        System.out.println("Transferred: " + amount);
    }
}
