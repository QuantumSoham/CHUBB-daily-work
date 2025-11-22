package com.baleno.loan;

public class LoanCalculator {
    double principal;
    double rate;
    int years;

    public LoanCalculator(double principal, double rate, int years) {
        this.principal = principal;
        this.rate = rate;
        this.years = years;
    }

    // Simple Interest Formula
    public double calculateSimpleInterest() {
        return (principal * rate * years) / 100;
    }

    // Compound Interest Formula
    public double calculateCompoundInterest() {
        double amount = principal * Math.pow((1 + rate / 100), years);
        return amount - principal;
    }

    // Total Amount (Simple)
    public double calculateTotalAmountSimple() {
        return principal + calculateSimpleInterest();
    }

    // Total Amount (Compound)
    public double calculateTotalAmountCompound() {
        return principal + calculateCompoundInterest();
    }

    // EMI (Monthly)
    public double calculateEMI(double totalAmount) {
        return totalAmount / (years * 12);
    }
}
