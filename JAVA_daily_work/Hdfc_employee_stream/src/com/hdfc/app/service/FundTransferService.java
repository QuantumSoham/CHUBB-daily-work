package com.hdfc.app.service;

import com.hdfc.app.model.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FundTransferService {

    private ExecutorService executor = Executors.newFixedThreadPool(5);

    public void processSalaries(Company company) {
        for (Employee emp : company.getEmployees()) {
            executor.submit(() -> {
                synchronized (company.getAccount()) {
                    if (company.getAccount().withdraw(emp.getSalary())) {
                        emp.getAccount().deposit(emp.getSalary());
                        System.out.println("Transferred ₹" + emp.getSalary() + " to " + emp.getName());
                    } else {
                        System.out.println("Insufficient funds for " + emp.getName());
                    }
                }
            });
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
