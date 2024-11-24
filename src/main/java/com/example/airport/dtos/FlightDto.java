package com.example.airport.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record FlightDto(
        Long id,
        String origin,
        String destination,
        Long airlane,
        Long airport,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        int capacity,
        List<Long> reserves) {
}
