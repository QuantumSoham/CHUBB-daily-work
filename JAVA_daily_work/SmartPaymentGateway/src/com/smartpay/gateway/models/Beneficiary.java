package com.smartpay.gateway.models;

public class Beneficiary {
    private String name;
    private String vpaOrAccount; // UPI VPA or account number

    public Beneficiary(String name, String vpaOrAccount) {
        if (name == null || vpaOrAccount == null) throw new IllegalArgumentException("Beneficiary fields cannot be null");
        this.name = name;
        this.vpaOrAccount = vpaOrAccount;
    }

    public String getName() { return name; }
    public String getVpaOrAccount() { return vpaOrAccount; }

    @Override
    public String toString() {
        return "Beneficiary{name='" + name + "', vpaOrAccount='" + vpaOrAccount + "'}";
    }
}
