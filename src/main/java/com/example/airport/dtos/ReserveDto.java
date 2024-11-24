package com.example.airport.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record ReserveDto(
        Long id,
        Long client,
        LocalDateTime reservationDate,
        int numberOfSeats,
        List<PassengerDto> passengers,
        List<FlightDto> flights) {
}
