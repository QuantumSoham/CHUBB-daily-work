package com.business;

import com.business.exceptions.*;

public class BankService {
    private double balance = 5000;

    public void withdraw(double amount) throws BusinessException {
        if (amount <= 0)
            throw new InvalidAccountException("Invalid withdrawal amount!");
        if (amount > balance)
            throw new InsufficientBalanceException("Insufficient funds in account!");
        if (amount > 2000)
            throw new LimitExceededException("Daily withdrawal limit exceeded!");
        
        balance -= amount;
        System.out.println("Withdrawal successful. Remaining balance: " + balance);
    }
}
