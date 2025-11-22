package com.smartship.model;

import java.util.ArrayList;
import java.util.List;

public class Agent {
    private String name;
    private String city;
    private List<PackageItem> assignedPackages = new ArrayList<>();

    public Agent(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getCity() { return city; }
    public String getName() { return name; }
    public List<PackageItem> getAssignedPackages() { return assignedPackages; }

    public void assignPackage(PackageItem pkg) {
        assignedPackages.add(pkg);
    }

    public int getLoad() {
        return assignedPackages.size();
    }

    @Override
    public String toString() {
        return name + " (" + city + ") - Load: " + getLoad();
    }
}
