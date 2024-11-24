package com.example.airport.exceptions;

import lombok.Data;

@Data
public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException() {
        this("Vuelo no encontrado.");
    }
    public FlightNotFoundException(String message) {
        super(message);
    }
    public FlightNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}