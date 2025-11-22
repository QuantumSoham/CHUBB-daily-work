package com.app.deadlock;

public class BookingTask implements Runnable {
    private final Room room;
    private final Payment payment;

    public BookingTask(Room room, Payment payment) {
        this.room = room;
        this.payment = payment;
    }

    @Override
    public void run() {
        String user = Thread.currentThread().getName();

        System.out.println(user + " attempting to lock Room...");
        synchronized (room) {
            System.out.println(user + " locked Room: " + room.getRoomName());

            try { Thread.sleep(100); } catch (InterruptedException e) {}

            System.out.println(user + " attempting to lock Payment...");
            synchronized (payment) {
                System.out.println(user + " locked Payment: " + payment.getPaymentId());
                System.out.println(user + " successfully booked the room and paid!");
            }
        }
    }
}
