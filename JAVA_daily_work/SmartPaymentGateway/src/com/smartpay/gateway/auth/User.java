package com.smartpay.gateway.auth;

public class User {
    private String username;
    private String maskedPassword; // stored masked for demo (DO NOT do this in prod)
    private boolean loggedIn;

    public User(String username, String password) {
        this.username = username;
        this.maskedPassword = mask(password);
        this.loggedIn = false;
    }

    private String mask(String s) {
        if (s == null) return null;
        if (s.length() <= 2) return "**";
        return s.charAt(0) + "***" + s.charAt(s.length()-1);
    }

    public String getUsername() { return username; }
    public String getMaskedPassword() { return maskedPassword; }
    public boolean isLoggedIn() { return loggedIn; }
    public void setLoggedIn(boolean loggedIn) { this.loggedIn = loggedIn; }
}
