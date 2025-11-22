package com.baleno.loan;

public class Car {
    String variant;
    String color;
    double cost;

    public Car(String variant, String color) {
        this.variant = variant;
        this.color = color;

        switch (variant.toLowerCase()) {
            case "delta":
                this.cost = 800000;
                break;
            case "beta":
                this.cost = 1000000;
                break;
            case "alfa":
                this.cost = 1200000;
                break;
            default:
                System.out.println("Invalid variant! Setting cost to 0.");
                this.cost = 0;
        }
    }

    public void displayDetails() {
        System.out.println("\nCar Details:");
        System.out.println("Variant: " + variant);
        System.out.println("Color: " + color);
        System.out.println("Base Cost: ₹" + cost);
    }
}
