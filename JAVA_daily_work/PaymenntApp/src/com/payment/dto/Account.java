package com.payment.dto;

public class Account implements Comparable<Account> {

	private String accountholdername;
	private String accountno;
	private String transcode;
	private String country;
	private String ifsccode;
	private double balance;

	public Account() {
	}

	public Account(String accountholdername, String accountno, String transcode, String country, String ifsccode,
			double balance) {
		this.accountholdername = accountholdername;
		this.accountno = accountno;
		this.transcode = transcode;
		this.country = country;
		this.ifsccode = ifsccode;
		this.balance = balance;
	}

	public String getAccountholdername() {
		return accountholdername;
	}

	public String getAccountno() {
		return accountno;
	}

	public String getTranscode() {
		return transcode;
	}

	public String getCountry() {
		return country;
	}

	public String getIfsccode() {
		return ifsccode;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	// ✅ Comparable — default sorting based on accountholdername
	@Override
	public int compareTo(Account o) {
		return this.accountholdername.compareToIgnoreCase(o.accountholdername);
	}

	// ✅ equals & hashCode — same object if name and accountno are same
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Account))
			return false;
		Account other = (Account) obj;
		return this.accountholdername.equalsIgnoreCase(other.accountholdername)
				&& this.accountno.equals(other.accountno);
	}

	@Override
	public int hashCode() {
		return (accountholdername + accountno).toLowerCase().hashCode();
	}

	@Override
	public String toString() {
		return String.format("Account[name=%s, accountno=%s, balance=%.2f, country=%s]", accountholdername, accountno,
				balance, country);
	}
}
