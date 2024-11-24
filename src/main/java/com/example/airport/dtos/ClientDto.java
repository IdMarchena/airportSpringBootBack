package com.example.airport.dtos;

import java.util.List;

public record ClientDto(
        Long id,
        String firstName,
        String lastName,
        String address,
        String cell,
        String email,
        String username,
        List<ReserveDto> reserves) {
}
