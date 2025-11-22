package com.app.deadlock;

public class HotelBookingDemo {
    public static void main(String[] args) throws InterruptedException {
        Room room = new Room("Room-101");
        Payment payment = new Payment("Pay123");

        // Thread 1 locks room first, then payment
        Thread user1 = new Thread(() -> {
            synchronized (room) {
                System.out.println("User-1 locked Room");

                try { Thread.sleep(100); } catch (InterruptedException e) {}

                synchronized (payment) {
                    System.out.println("User-1 locked Payment");
                    System.out.println("User-1 booking complete");
                }
            }
        }, "User-1");

        // Thread 2 locks payment first, then room
        Thread user2 = new Thread(() -> {
            synchronized (payment) {
                System.out.println("User-2 locked Payment");

                try { Thread.sleep(100); } catch (InterruptedException e) {}

                synchronized (room) {
                    System.out.println("User-2 locked Room");
                    System.out.println("User-2 booking complete");
                }
            }
        }, "User-2");

        user1.start();
        user2.start();

        user1.join();
        user2.join();

        System.out.println("Booking process finished!");
    }
}
