package com.app.process;

import com.app.dto.Customer;
import com.app.dto.NEFTProcessFund;

public class FundTransfer {
    public static void main(String[] args) {
        Customer c1 = new Customer("James", "james@gmail.com", "43432432442", 4343);
        Customer c2 = new Customer("Robin", "robin@gmail.com", "43432432441", 50000);

        NEFTProcessFund neftobj = new NEFTProcessFund();

        System.out.println("Before transfer: ");
        System.out.println("James balance: " + c1.getAmountbalance());
        System.out.println("Robin balance: " + c2.getAmountbalance());

        try {
            neftobj.processFund(c1, c2, 3000.00);
        } catch (Exception e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }

        System.out.println("After transfer: ");
        System.out.println("James balance: " + c1.getAmountbalance());
        System.out.println("Robin balance: " + c2.getAmountbalance());
    }
}

