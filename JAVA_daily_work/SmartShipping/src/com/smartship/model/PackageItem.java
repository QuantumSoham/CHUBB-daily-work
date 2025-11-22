package com.smartship.model;

public class PackageItem implements Comparable<PackageItem> {
    private String id;
    private String destinationCity;
    private int priority;

    public PackageItem(String id, String destinationCity, int priority) {
        this.id = id;
        this.destinationCity = destinationCity;
        this.priority = priority;
    }

    public String getDestinationCity() { return destinationCity; }
    public int getPriority() { return priority; }
    public String getId() { return id; }

    @Override
    public int compareTo(PackageItem other) {
        return Integer.compare(other.priority, this.priority); // Higher priority first
    }

    @Override
    public String toString() {
        return id + " -> " + destinationCity + " (Priority: " + priority + ")";
    }
}
