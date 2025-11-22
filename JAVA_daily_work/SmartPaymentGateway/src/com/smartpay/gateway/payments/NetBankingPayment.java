package com.smartpay.gateway.payments;

import com.smartpay.gateway.exceptions.*;

public class NetBankingPayment extends PaymentMethod {
    private String accountNumber;
    private String password;

    public NetBankingPayment(String payer, String accountNumber, String password) {
        super(payer);
        this.accountNumber = accountNumber;
        this.password = password;
    }

    @Override
    public boolean processPayment(double amount) throws InvalidAmountException, InvalidCredentialsException, PaymentGatewayTimeoutException {
        if (amount <= 0) throw new InvalidAmountException("Invalid netbanking amount");
        if (accountNumber == null || accountNumber.length() < 8) throw new InvalidCredentialsException("Invalid account number");
        if (password == null || password.length() < 6) {
            System.err.println("[NETBANKING-LOG] poor password attempt for account=" + mask(accountNumber));
            throw new InvalidCredentialsException("Invalid bank password");
        }
        // simulate timeout
        if (((int)amount) % 7 == 0) throw new PaymentGatewayTimeoutException("NetBanking gateway timed out");

        System.out.println("NetBankingPayment: Transferred Rs." + amount + " from acc=" + mask(accountNumber));
        return true;
    }

    private String mask(String s) {
        if (s == null) return "null";
        if (s.length() <= 4) return "****";
        return "****" + s.substring(s.length()-4);
    }
}
