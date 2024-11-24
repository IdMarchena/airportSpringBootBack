package com.example.airport.exceptions;

import lombok.Data;

@Data
public class AirportNotFoundException extends RuntimeException {
    public AirportNotFoundException() {
        this("Aereopuerto no econtrado.");
    }
    public AirportNotFoundException(String message) {
        super(message);
    }
    public AirportNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
