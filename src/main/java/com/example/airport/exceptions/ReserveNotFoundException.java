package com.example.airport.exceptions;

import lombok.Data;

@Data
public class ReserveNotFoundException extends RuntimeException {
    public ReserveNotFoundException() {
        this("Reserva no encontrada.");
    }
    public ReserveNotFoundException(String message) {
        super(message);
    }
    public ReserveNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
