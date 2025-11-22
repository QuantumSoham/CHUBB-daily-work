import java.util.*;

class Room {
    String type;
    double price;
    int available;

    Room(String type, double price, int available) {
        this.type = type;
        this.price = price;
        this.available = available;
    }

    boolean bookRoom() {
        if (available > 0) {
            available--;
            System.out.println("✅ Room booked successfully!");
            return true;
        } else {
            System.out.println("❌ Sorry, no " + type + " rooms available.");
            return false;
        }
    }

    void showRoomDetails() {
        System.out.println("Room Type: " + type + " | Price: ₹" + price + " | Available: " + available);
    }
}