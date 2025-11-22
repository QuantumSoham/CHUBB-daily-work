//package com.smartship.main;

import com.smartship.model.*;
import com.smartship.service.*;
import com.smartship.exception.*;

public class SmartShipMain {
    public static void main(String[] args) {
        try {
            DeliveryManager manager = new DeliveryManager();

            manager.addAgent(new Agent("Rohan", "Delhi"));
            manager.addAgent(new Agent("Amit", "Mumbai"));
            manager.addAgent(new Agent("Priya", "Delhi"));

            manager.addPackage(new PackageItem("P1", "Delhi", 5));
            manager.addPackage(new PackageItem("P2", "Delhi", 3));
            manager.addPackage(new PackageItem("P3", "Mumbai", 4));
            manager.addPackage(new PackageItem("P4", "Delhi", 1));

            manager.assignPackages();

        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }
}
