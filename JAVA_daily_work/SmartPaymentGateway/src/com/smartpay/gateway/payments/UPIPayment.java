package com.smartpay.gateway.payments;

import com.smartpay.gateway.exceptions.*;

public class UPIPayment extends PaymentMethod {
    private String vpa;
    private String pin;

    public UPIPayment(String payer, String vpa, String pin) {
        super(payer);
        this.vpa = vpa;
        this.pin = pin;
    }

    @Override
    public boolean processPayment(double amount) throws InvalidAmountException, InvalidCredentialsException, PaymentGatewayTimeoutException {
        if (amount <= 0) throw new InvalidAmountException("Amount cannot be negative or zero for UPI");
        if (vpa == null || !vpa.contains("@")) throw new InvalidCredentialsException("Invalid VPA format");
        if (pin == null || pin.length() < 4) {
            // mask pin in logs
            System.err.println("[UPI-LOG] Invalid PIN attempt for vpa=" + mask(vpa));
            throw new InvalidCredentialsException("Invalid UPI PIN");
        }
        // simulate gateway hiccup
        if (((int)amount) % 11 == 0) throw new PaymentGatewayTimeoutException("UPI gateway busy");

        System.out.println("UPIPayment: Sent Rs." + amount + " from " + vpa);
        return true;
    }

    private String mask(String s) {
        if (s == null) return "null";
        if (s.length() <= 3) return "***";
        return s.substring(0,2) + "***" + s.substring(s.length()-2);
    }
}
