package com.example.airport.dtos;

import java.util.List;

public record AirlaneDto(
        Long id,
        String name,
        String airlaneCode,
        String originCountry,
        List<FlightDto> flights) {
}
