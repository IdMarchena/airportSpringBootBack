package com.example.airport.exceptions;

import lombok.Data;

@Data
public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException() {
        this("Cliente no encontrado");
    }
    public ClientNotFoundException(String message) {
        super(message);
    }
    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
