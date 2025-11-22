package com.smartpay.gateway.auth;

import com.smartpay.gateway.exceptions.InvalidCredentialsException;
import java.util.HashMap;
import java.util.Map;

public class AuthService {
    // For demo only. Passwords stored in-memory in a map (username -> password).
    private Map<String, String> store = new HashMap<>();

    public AuthService() {
        // seed an account
        store.put("aashish", "upipin123");
        store.put("user1", "pass1");
    }

    public User login(String username, String password) throws InvalidCredentialsException {
        if (username == null || password == null) throw new InvalidCredentialsException("Username or password is null");
        String correct = store.get(username);
        if (correct == null || !correct.equals(password)) {
            // log masked info
            System.err.println("[AUTH-LOG] Failed login attempt for user: " + mask(username));
            throw new InvalidCredentialsException("Invalid username or password.");
        }
        User u = new User(username, password);
        u.setLoggedIn(true);
        System.out.println("Login successful: " + username);
        return u;
    }

    private String mask(String s) {
        if (s == null) return "null";
        if (s.length() <= 2) return "**";
        return s.charAt(0) + "***" + s.charAt(s.length()-1);
    }
}
