package com.example.flight.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PassengerDto {
    private String name;
    private String gender;
    private Integer age;
}
