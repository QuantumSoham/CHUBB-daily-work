package com.example.flight.controller;

import com.example.flight.dto.BookingRequest;
import com.example.flight.dto.BookingResponse;
import com.example.flight.model.Booking;
import com.example.flight.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1.0/flight")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    // Book ticket
    @PostMapping("/booking/{flightId}")
    public Mono<ResponseEntity<BookingResponse>> bookTicket(
            @PathVariable Long flightId,
            @Valid @RequestBody BookingRequest request) {

        return service.createBooking(flightId, request)
                .map(pnr -> ResponseEntity.ok(new BookingResponse(pnr, "Booking successful")))
                .onErrorResume(e -> Mono.just(ResponseEntity.internalServerError()
                        .body(new BookingResponse(null, "Failed: " + e.getMessage()))));
    }

    // Get booking by PNR
    @GetMapping("/ticket/{pnr}")
    public Mono<ResponseEntity<Booking>> getByPnr(@PathVariable String pnr) {
        return service.getBookingByPnr(pnr)
                .map(b -> ResponseEntity.ok(b))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
