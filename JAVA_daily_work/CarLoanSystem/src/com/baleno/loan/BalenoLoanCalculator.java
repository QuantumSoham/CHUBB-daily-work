package com.baleno.loan;

import java.util.Scanner;

public class BalenoLoanCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- Car Details ---
        System.out.print("Enter variant (Delta/Beta/Alfa): ");
        String variant = sc.nextLine();

        System.out.print("Enter color: ");
        String color = sc.nextLine();

        Car car = new Car(variant, color);
        car.displayDetails();

        // --- Down Payment and Loan ---
        System.out.print("\nEnter down payment amount: ");
        double downPayment = sc.nextDouble();

        if (downPayment > car.cost) {
            System.out.println("Down payment cannot exceed car cost!");
            sc.close();
            return;
        }

        double loanAmount = car.cost - downPayment;
        System.out.println("Loan Amount to be Financed: ₹" + loanAmount);

        System.out.print("Enter rate of interest (%): ");
        double rate = sc.nextDouble();

        System.out.print("Enter tenure (3 or 5 years): ");
        int years = sc.nextInt();

        sc.nextLine(); // consume newline
        System.out.print("Choose interest type (simple/compound): ");
        String type = sc.nextLine().toLowerCase();

        // --- Loan Calculations ---
        LoanCalculator loan = new LoanCalculator(loanAmount, rate, years);
        double total, emi;

        if (type.equals("compound")) {
            total = loan.calculateTotalAmountCompound();
            emi = loan.calculateEMI(total);
            System.out.println("\n--- Loan Summary (Compound Interest) ---");
        } else {
            total = loan.calculateTotalAmountSimple();
            emi = loan.calculateEMI(total);
            System.out.println("\n--- Loan Summary (Simple Interest) ---");
        }

        // --- Output ---
        System.out.println("Car Variant: " + variant);
        System.out.println("Base Cost: ₹" + car.cost);
        System.out.println("Down Payment: ₹" + downPayment);
        System.out.println("Loan Amount: ₹" + loanAmount);
        System.out.println("Interest Rate: " + rate + "%");
        System.out.println("Tenure: " + years + " years");
        System.out.println("Total Payable after " + years + " years: ₹" + total);
        System.out.println("EMI per month: ₹" + emi);

        sc.close();
    }
}
