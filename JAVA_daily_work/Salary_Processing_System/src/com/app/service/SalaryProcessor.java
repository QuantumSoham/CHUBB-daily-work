package com.app.service;

import com.app.model.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class SalaryProcessor {

    private CompanyAccount companyAccount;
    private Map<String, EmployeeAccount> employees = new ConcurrentHashMap<>();
    private List<Transaction> transactions = new ArrayList<>();

    public SalaryProcessor(CompanyAccount companyAccount) {
        this.companyAccount = companyAccount;
    }

    public void loadTransactions(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String txnId = parts[0].trim();
                    String name = parts[1].trim();
                    String acc = parts[2].trim();
                    double amt = Double.parseDouble(parts[3].trim());
                    transactions.add(new Transaction(txnId, name, acc, amt));
                    employees.putIfAbsent(acc, new EmployeeAccount(name, acc));
                }
            }
        }
    }

    public void processTransactions(String outputFile) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<String> results = Collections.synchronizedList(new ArrayList<>());

        for (Transaction txn : transactions) {
            executor.submit(() -> {
                synchronized (companyAccount) {
                    boolean success = companyAccount.transferSalary(txn.getAmount());
                    if (success) {
                        employees.get(txn.getAccountNumber()).credit(txn.getAmount());
                        results.add(txn.getTransactionId() + " | SUCCESS | " + txn.getAmount());
                    } else {
                        results.add(txn.getTransactionId() + " | FAILED | Insufficient Balance");
                    }
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write("Processed Transactions:\n");
            for (String result : results) {
                bw.write(result + "\n");
            }
            bw.write("\nFinal Company Balance: " + companyAccount.getBalance() + "\n");
            bw.write("\nEmployee Balances:\n");
            for (EmployeeAccount emp : employees.values()) {
                bw.write(emp.toString() + "\n");
            }
        }
    }
}
