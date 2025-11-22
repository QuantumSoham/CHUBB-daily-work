package com.soham.weekly;

import java.util.Random;
import java.util.concurrent.*;

public class ApiSimulator {
    private final Random rnd = new Random();

    /**
     * Simulates an async API call to fetch discount for a product.
     * Returns strings like "10%" or "0%".
     */
    public CompletableFuture<String> getDiscountAsync(String product, Executor ex) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // simulate variable latency
                int sleep = 300 + rnd.nextInt(700);
                Thread.sleep(sleep);
                // fake discount logic
                int d = (product.hashCode() & 0xff) % 30; // 0-29
                return d + "%";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "0%";
            }
        }, ex);
    }
}
