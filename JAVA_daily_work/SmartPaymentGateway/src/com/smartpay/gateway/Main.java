package com.smartpay.gateway;

import com.smartpay.gateway.auth.*;
import com.smartpay.gateway.exceptions.*;
import com.smartpay.gateway.payments.*;
import com.smartpay.gateway.interfaces.*;
import com.smartpay.gateway.models.Beneficiary;

import java.util.*;

public class Main {
    private static AuthService auth = new AuthService();
    private static User currentUser = null;
    private static List<Beneficiary> beneficiaries = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== SmartPayment Gateway (Console Demo) ===");
        boolean running = true;

        // seed beneficiary
        beneficiaries.add(new Beneficiary("Alice","alice@bank"));

        while (running) {
            printMenu();
            String choice = sc.nextLine();
            try {
                switch (choice) {
                    case "1": handleLogin(); break;
                    case "2": handleAddBeneficiary(); break;
                    case "3": listBeneficiaries(); break;
                    case "4": handleMakePayment(); break;
                    case "5": handleRefund(); break;
                    case "6": running = false; System.out.println("Exiting. Goodbye!"); break;
                    default: System.out.println("Unknown option. Try again.");
                }
            } catch (Exception e) {
                // Generic catch to ensure menu continues after exceptions (negative testing)
                System.err.println("[ERROR] " + e.getClass().getSimpleName() + ": " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("1) Login");
        System.out.println("2) Add Beneficiary");
        System.out.println("3) List Beneficiaries");
        System.out.println("4) Make Payment");
        System.out.println("5) Refund (wallet only)");
        System.out.println("6) Exit");
        System.out.print("Choose: ");
    }

    private static void handleLogin() {
        System.out.print("username: ");
        String u = sc.nextLine();
        System.out.print("password: ");
        String p = sc.nextLine();
        try {
            currentUser = auth.login(u, p);
        } catch (InvalidCredentialsException ice) {
            System.err.println("Login failed: " + ice.getMessage());
        }
    }

    private static void handleAddBeneficiary() {
        System.out.print("Beneficiary name: ");
        String name = sc.nextLine();
        System.out.print("VPA or account: ");
        String vpa = sc.nextLine();
        try {
            Beneficiary b = new Beneficiary(name, vpa);
            beneficiaries.add(b);
            System.out.println("Added beneficiary: " + b);
        } catch (IllegalArgumentException iae) {
            System.err.println("Invalid beneficiary data: " + iae.getMessage());
        }
    }

    private static void listBeneficiaries() {
        if (beneficiaries.isEmpty()) System.out.println("No beneficiaries.");
        for (int i=0;i<beneficiaries.size();i++) {
            System.out.println(i + ") " + beneficiaries.get(i));
        }
    }

    private static Beneficiary findBeneficiary(int idx) throws BeneficiaryNotFoundException {
        if (idx < 0 || idx >= beneficiaries.size()) throw new BeneficiaryNotFoundException("Beneficiary index out of range: " + idx);
        return beneficiaries.get(idx);
    }

    private static void handleMakePayment() {
        System.out.println("Choose payment type: 1) CreditCard 2) UPI 3) Wallet 4) NetBanking");
        String t = sc.nextLine();
        System.out.print("Amount: ");
        String amtStr = sc.nextLine();
        double amount = 0;
        try {
            amount = Double.parseDouble(amtStr);
        } catch (NumberFormatException nfe) {
            System.err.println("Invalid amount format");
            return;
        }

        PaymentMethod pm = null;
        try {
            switch (t) {
                case "1":
                    System.out.print("Card number: "); String cn = sc.nextLine();
                    System.out.print("CVV: "); String cvv = sc.nextLine();
                    pm = new CreditCardPayment(currentUser==null?"guest":currentUser.getUsername(), cn, cvv);
                    break;
                case "2":
                    System.out.print("VPA: "); String vpa = sc.nextLine();
                    System.out.print("UPI PIN: "); String pin = sc.nextLine();
                    pm = new UPIPayment(currentUser==null?"guest":currentUser.getUsername(), vpa, pin);
                    break;
                case "3":
                    System.out.print("Wallet initial balance (for demo): "); String balS = sc.nextLine();
                    double bal = 0; try { bal = Double.parseDouble(balS);} catch (Exception e) {System.err.println("Invalid balance; using 0");}
                    pm = new WalletPayment(currentUser==null?"guest":currentUser.getUsername(), bal);
                    break;
                case "4":
                    System.out.print("Account number: "); String acc = sc.nextLine();
                    System.out.print("Password: "); String pw = sc.nextLine();
                    pm = new NetBankingPayment(currentUser==null?"guest":currentUser.getUsername(), acc, pw);
                    break;
                default:
                    System.out.println("Unknown payment type"); return;
            }

            // simulate choice of beneficiary
            System.out.print("Send to beneficiary index (or -1 to skip): ");
            String bi = sc.nextLine();
            int bidx = -1; try { bidx = Integer.parseInt(bi);} catch (Exception ignored) {}
            if (bidx >= 0) {
                try {
                    Beneficiary b = findBeneficiary(bidx);
                    System.out.println("Paying to: " + b);
                } catch (BeneficiaryNotFoundException bnfe) {
                    System.err.println("Beneficiary not found: " + bnfe.getMessage());
                    return;
                }
            }

            // attempt payment with retry and catch specific exceptions
            try {
                boolean success = pm.processPayment(amount);
                if (success) System.out.println("Transaction successful.");
            } catch (InvalidAmountException | InsufficientBalanceException | InvalidCredentialsException | PaymentGatewayTimeoutException e) {
                System.err.println("Transaction error: " + e.getMessage());
                // if retryable, attempt retry once
                if (pm != null && pm.retry()) {
                    try {
                        boolean success = pm.processPayment(amount);
                        if (success) System.out.println("Transaction successful on retry.");
                    } catch (Exception e2) {
                        System.err.println("Retry failed: " + e2.getMessage());
                        // wrap into TransactionFailedException and rethrow to be caught by outer loop
                        throw new TransactionFailedException("Retry failed", e2);
                    }
                }
            }

        } catch (TransactionFailedException tfe) {
            System.err.println("Transaction finally failed: " + tfe.getMessage());
        } catch (NullPointerException npe) {
            System.err.println("Null pointer encountered - possibly no login or missing input");
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void handleRefund() {
        System.out.println("Refunds supported for Wallet only in this demo.");
        System.out.print("Refund amount: ");
        String s = sc.nextLine();
        double amt = 0;
        try { amt = Double.parseDouble(s); } catch (NumberFormatException nfe) { System.err.println("Invalid number"); return; }
        // For demo, create a WalletPayment instance and call refund
        WalletPayment w = new WalletPayment(currentUser==null?"guest":currentUser.getUsername(), 0);
        if (w.refund(amt)) System.out.println("Refund completed to wallet"); else System.err.println("Refund failed");
    }
}
