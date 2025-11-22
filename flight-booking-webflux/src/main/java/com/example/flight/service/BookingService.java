package com.example.flight.service;

import com.example.flight.dto.BookingRequest;
import com.example.flight.model.Booking;
import com.example.flight.model.Passenger;
import com.example.flight.repository.BookingRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository repo;

    public BookingService(BookingRepository repo) {
        this.repo = repo;
    }

    public Mono<String> createBooking(Long flightId, BookingRequest req) {
        return Mono.fromCallable(() -> {
            // Basic PNR generation
            String pnr = generatePnr();

            Booking booking = Booking.builder()
                    .pnr(pnr)
                    .flightId(flightId)
                    .userName(req.getUserName())
                    .userEmail(req.getUserEmail())
                    .seatsBooked(req.getSeats())
                    .mealPref(req.getMealPref())
                    .createdAt(Instant.now())
                    .build();

            // attach passengers
            var passengers = req.getPassengers().stream().map(pdto -> {
                Passenger p = Passenger.builder()
                        .name(pdto.getName())
                        .gender(pdto.getGender())
                        .age(pdto.getAge())
                        .build();
                p.setBooking(booking);
                return p;
            }).collect(Collectors.toList());

            booking.setPassengers(passengers);

            var saved = repo.save(booking);
            return saved.getPnr();
        }).subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Booking> getBookingByPnr(String pnr) {
        return Mono.fromCallable(() -> repo.findByPnr(pnr).orElse(null))
                   .subscribeOn(Schedulers.boundedElastic());
    }

    private String generatePnr(){
        // 6 char alphanumeric uppercase, time-based seed to avoid collision
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int idx = (int)(Math.random() * chars.length());
            sb.append(chars.charAt(idx));
        }
        sb.append(System.currentTimeMillis() % 1000); // add small timestamp suffix
        return sb.toString().toUpperCase();
    }
}
