package com.business;

import com.business.exceptions.*;

public class Main {
    public static void main(String[] args) {
        BankService service = new BankService();
        double[] testAmounts = {500, 2500, 7000, -100};

        for (double amt : testAmounts) {
            try {
                System.out.println("\nTrying to withdraw: " + amt);
                service.withdraw(amt);
            } catch (BusinessException e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }
}
