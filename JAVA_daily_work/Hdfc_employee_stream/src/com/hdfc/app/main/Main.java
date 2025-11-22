package com.hdfc.app.main;

import com.hdfc.app.model.*;
import com.hdfc.app.service.FundTransferService;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Account companyAccount = new Account("HDFC-COMP-001", 500000);
        List<Employee> employees = List.of(
                new Employee("Sam", 50000, new Account("EMP001", 0)),
                new Employee("Soham", 60000, new Account("EMP002", 0)),
                new Employee("Riya", 55000, new Account("EMP003", 0)),
                new Employee("Arjun", 70000, new Account("EMP004", 0))
        );

        Company company = new Company("HDFC Ltd", companyAccount, employees);
        FundTransferService transferService = new FundTransferService();

        System.out.println("Starting Salary Transfer...\n");
        transferService.processSalaries(company);

        transferService.shutdown();

        
        double totalPaid = company.getEmployees()
                .stream()
                .collect(Collectors.summingDouble(Employee::getSalary));

        System.out.println("Total Salary Paid by " + company.getName() + "RS. " + totalPaid);
        System.out.println("Remaining Balance in Company Account: Rs." + company.getAccount().getBalance());
    }
}
