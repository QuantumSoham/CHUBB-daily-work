package com.soham.weekly;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.*;

public class CsvReportGenerator {
    private final Path csvPath;
    private List<Sale> sales = new ArrayList<>();
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public CsvReportGenerator(Path csvPath) {
        this.csvPath = csvPath;
    }

    public void load() {
        try (Stream<String> lines = Files.lines(csvPath)) {
            sales = lines.skip(1) // header
                    .map(this::parse)
                    .flatMap(Optional::stream)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV: " + e.getMessage(), e);
        }
    }

    private Optional<Sale> parse(String line) {
        // simple CSV: date,orderId,product,customer,qty,price
        String[] a = line.split(",");
        if (a.length < 6) return Optional.empty();
        try {
            LocalDate d = LocalDate.parse(a[0], DF);
            String order = a[1];
            String prod = a[2];
            String cust = a[3];
            int qty = Integer.parseInt(a[4]);
            double price = Double.parseDouble(a[5]);
            return Optional.of(new Sale(d, order, prod, cust, qty, price));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Map<String, Double> totalSalesByProduct() {
        return sales.stream()
                .collect(Collectors.groupingBy(s -> s.product,
                        Collectors.summingDouble(s -> s.price * s.qty)));
    }

    public Map<String, Double> totalSalesByMonth() {
        return sales.stream()
                .collect(Collectors.groupingBy(
                        s -> s.date.getYear() + "-" + String.format("%02d", s.date.getMonthValue()),
                        Collectors.summingDouble(s -> s.price * s.qty)
                ));
    }

    public List<String> topNProducts(int n) {
        return totalSalesByProduct().entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(n)
                .map(Map.Entry::getKey)
                .toList();
    }

    // simple POJO
    static class Sale {
        LocalDate date;
        String orderId;
        String product;
        String customer;
        int qty;
        double price;
        Sale(LocalDate d, String o, String p, String c, int q, double pr) {
            date = d; orderId = o; product = p; customer = c; qty = q; price = pr;
        }
        public String toString(){ return product + "(" + qty + "x" + price + ")"; }
    }
}
