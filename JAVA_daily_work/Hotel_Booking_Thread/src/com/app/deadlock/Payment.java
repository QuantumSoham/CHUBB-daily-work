package com.app.deadlock;

public class Payment {
    private final String paymentId;

    public Payment(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentId() {
        return paymentId;
    }
}
