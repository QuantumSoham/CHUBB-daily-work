package com.example.flight.dto;

import lombok.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BookingRequest {
    @NotBlank
    private String userName;

    @Email @NotBlank
    private String userEmail;

    @NotNull @Min(1)
    private Integer seats;

    @NotEmpty
    private List<PassengerDto> passengers;

    private String mealPref; // "Veg" or "Non-Veg"
}
