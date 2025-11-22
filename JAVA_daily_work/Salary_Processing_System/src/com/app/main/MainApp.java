package com.app.main;

import com.app.model.*;
import com.app.service.*;
import java.io.*;

public class MainApp {
    public static void main(String[] args) {
        try {
            CompanyAccount company = new CompanyAccount(1000000); // Initial Company Balance
            SalaryProcessor processor = new SalaryProcessor(company);

            processor.loadTransactions("transactions");
            processor.processTransactions("processed_transactions.txt");

            System.out.println("✅ Salary Processing Completed. Check processed_transactions.txt for details.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
