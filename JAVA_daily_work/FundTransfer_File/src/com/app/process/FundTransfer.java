package com.app.process;

import java.io.*;

import java.util.*;
import com.app.dto.Customer;
import com.app.dto.NEFTProcessFund;


public class FundTransfer {

    public static void main(String[] args) {
        String filePath = "transactions"; // or .csv
        double totalHdfcPaid = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            NEFTProcessFund neft = new NEFTProcessFund();

            while ((line = br.readLine()) != null) {
                // Split by comma
                String[] data = line.split(",");

                // sender: name,country,accNo,ifsc,balance
                // receiver: balance,mode,name,country,accNo,ifsc
                String senderName = data[0];
                String senderCountry = data[1];
                String senderAccNo = data[2];
                String senderIfsc = data[3];
                double senderBalance = Double.parseDouble(data[4]);

                double transferAmount = Double.parseDouble(data[5]);
                String transferMode = data[6];

                String receiverName = data[7];
                String receiverCountry = data[8];
                String receiverAccNo = data[9];
                String receiverIfsc = data[10];

                // Create customers
                Customer sender = new Customer(senderName, senderCountry, senderAccNo, senderIfsc, senderBalance);
                Customer receiver = new Customer(receiverName, receiverCountry, receiverAccNo, receiverIfsc, 0);

                // Validate and transfer
                if (neft.validateCustomer(sender)) {
                    try {
                        neft.processFund(sender, receiver, transferAmount);
                        if (senderIfsc.startsWith("HDFC")) {
                            totalHdfcPaid += transferAmount;
                        }
                    } catch (Exception e) {
                        System.out.println("❌ Transaction failed for " + senderName + ": " + e.getMessage());
                    }
                } else {
                    System.out.println("❌ Invalid customer: " + senderName);
                }
            }

            System.out.println("\n🏦 Total amount paid by HDFC Bank: ₹" + totalHdfcPaid);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
