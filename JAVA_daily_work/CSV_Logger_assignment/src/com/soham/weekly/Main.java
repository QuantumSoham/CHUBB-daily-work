package com.soham.weekly;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting Weekly Assignment project...\n");
        Path csv = Paths.get("resources/sales.csv");
        Path log = Paths.get("resources/logs.txt");

        CsvReportGenerator gen = new CsvReportGenerator(csv);
        ApiSimulator api = new ApiSimulator();

        // 1) Run CSV parsing + generate a couple of stream reports
        System.out.println("-- CSV Reports --");
        gen.load(); // lazy load from file
        Map<String, Double> salesByProduct = gen.totalSalesByProduct();
        System.out.println("Total sales by product: " + salesByProduct);

        Map<String, Double> salesByMonth = gen.totalSalesByMonth();
        System.out.println("Total sales by month: " + salesByMonth);

        // 2) Simulate async API calls for discounts for top 3 products
        System.out.println("\n-- Async API calls (discounts) --");
        List<String> topProducts = gen.topNProducts(3);
        ExecutorService ex = Executors.newFixedThreadPool(4);

        List<CompletableFuture<String>> futures = topProducts.stream()
                .map(p -> api.getDiscountAsync(p, ex)
                        .thenApply(d -> String.format("%s -> discount=%s", p, d)))
                .toList();

        // Combine all and print results when ready
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenRun(() -> futures.forEach(f -> {
                    try { System.out.println(f.get()); } catch (Exception e) { e.printStackTrace(); }
                })).get();

        ex.shutdown();

        // 3) Parse logs using streams + lambdas
        System.out.println("\n-- Log parsing --");
        LogParser lp = new LogParser(log);
        lp.load();
        System.out.println("Level counts: " + lp.levelCounts());
        System.out.println("Top 5 error messages: " + lp.topErrors(5));

        System.out.println("\nAll done. Happy coding, Soham.");
    }
}
