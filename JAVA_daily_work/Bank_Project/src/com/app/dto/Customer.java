package com.app.dto;

public class Customer {

	private String name;
	private String email;
	private String accountno;
	private double amountbalance;

	public Customer() {
		System.out.println("Inside default constructor");
	}

	public Customer(String custname, String custemail, String accountdetails, double balance) {
		this.name = custname;
		this.email = custemail;
		this.accountno = accountdetails;
		this.amountbalance = balance;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getAccountno() {
		return accountno;
	}

	public double getAmountbalance() {
		return amountbalance;
	}

	public void setAmount(double amount) {
		this.amountbalance = amount;
	}
}
