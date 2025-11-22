package com.smartpay.gateway.payments;

import com.smartpay.gateway.exceptions.*;

public class CreditCardPayment extends PaymentMethod {
    private String cardNumber;
    private String cvv;

    public CreditCardPayment(String payer, String cardNumber, String cvv) {
        super(payer);
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }

    @Override
    public boolean processPayment(double amount) throws InvalidAmountException, PaymentGatewayTimeoutException, TransactionFailedException {
        if (amount <= 0) throw new InvalidAmountException("Amount must be > 0");
        // simulate invalid card
        if (cardNumber == null || cardNumber.length() < 12) {
            throw new TransactionFailedException("Invalid card number", new IllegalArgumentException("cardNumber invalid"));
        }
        // simulate occasional timeout (demo using mod check)
        if (((int)amount) % 13 == 0) throw new PaymentGatewayTimeoutException("Timeout processing credit card");

        System.out.println("CreditCardPayment: Charged amount Rs." + amount + " to card ending " + (cardNumber.length()>4 ? cardNumber.substring(cardNumber.length()-4) : cardNumber));
        return true;
    }
}
