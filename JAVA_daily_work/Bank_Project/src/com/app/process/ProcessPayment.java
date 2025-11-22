package com.app.process;

import com.app.dto.Customer;

public abstract class ProcessPayment implements EmailProcessing {

	public int processcount;

	public static void processFund(Customer initiator, Customer bene, double amount) throws Exception {
		System.out.println("Hi this is first program");

		if (initiator != null && bene != null) {
			if (initiator.getAmountbalance() > amount) {
				double balanceamount = initiator.getAmountbalance() - amount;
				initiator.setAmount(balanceamount);
				bene.setAmount(bene.getAmountbalance() + amount);
			} else {
				System.out.println("Not having sufficient balance");
			}
		}
	}

	public abstract boolean validateCustomer(Customer c1);

	public boolean validateEmail(Customer c1) {
		return c1.getEmail() != null && c1.getEmail().contains("@");
	}

	public void intializeEmailServer() {
		System.out.println("Initialize server with Azure email service");
	}
}
