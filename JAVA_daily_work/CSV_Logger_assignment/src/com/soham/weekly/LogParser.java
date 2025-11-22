package com.soham.weekly;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class LogParser {
    private final Path logPath;
    private List<String> lines = new ArrayList<>();

    public LogParser(Path p) { this.logPath = p; }

    public void load() {
        try {
            lines = Files.readAllLines(logPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Long> levelCounts() {
        // assume logs like "INFO [2025-11-12] msg" or "ERROR ... "
        return lines.stream()
                .map(l -> {
                    String t = l.split(" ")[0];
                    return t;
                })
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public List<String> topErrors(int n) {
        return lines.stream()
                .filter(l -> l.startsWith("ERROR"))
                .map(l -> {
                    // try to remove timestamp and level
                    String[] parts = l.split(" - ", 2);
                    return parts.length > 1 ? parts[1] : l;
                })
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(n)
                .map(Map.Entry::getKey)
                .toList();
    }
}
