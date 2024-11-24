package com.example.airport.dtos;

public record PassengerDto(
        Long id,
        String firstName,
        String lastName,
        String address,
        String cell,
        String email,
        String identificationNumber,
        Long reserve) {
}
