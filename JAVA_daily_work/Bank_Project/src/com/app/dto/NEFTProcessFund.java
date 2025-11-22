package com.app.dto;

import com.app.process.ProcessPayment;
import com.app.process.SMSProcessing;

public class NEFTProcessFund extends ProcessPayment implements SMSProcessing {

	public static void processFund(Customer initiator, Customer bene, double amount) throws AccountBalaneException {
		System.out.println("Hi this is first program in NEFTProcessFund");

		if (initiator != null && bene != null) {
			if (initiator.getAmountbalance() > amount && amount < 2000000) {
				double balanceamount = initiator.getAmountbalance() - amount;
				initiator.setAmount(balanceamount);
				bene.setAmount(bene.getAmountbalance() + amount);
				System.out.println("Process fund immediately");
			} else {
				throw new AccountBalaneException("Not having sufficient balance or not a NEFT payment");
			}
		}
	}

	@Override
	public boolean validateCustomer(Customer c1) {
		return c1.getName() != null && !c1.getName().equals("Bin Laden");
	}

	@Override
	public boolean validateEmail() {
		return false;
	}

	@Override
	public boolean sendSMS(Customer c1) {
		System.out.println("Sent SMS to customer " + c1.getName());
		return true;
	}
}
