package com.smartpay.gateway.payments;

import com.smartpay.gateway.exceptions.*;
import com.smartpay.gateway.interfaces.Retryable;

public abstract class PaymentMethod implements Retryable {
    protected String payer;

    public PaymentMethod(String payer) {
        this.payer = payer;
    }

    public abstract boolean processPayment(double amount) throws InvalidAmountException, InsufficientBalanceException, PaymentGatewayTimeoutException, InvalidCredentialsException, TransactionFailedException;

    @Override
    public boolean retry() {
        // default retry policy: single retry
        System.out.println("Retrying transaction (default) for payer=" + payer);
        return true;
    }
}
