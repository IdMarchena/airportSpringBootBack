package com.example.airport.exceptions;

import lombok.Data;

@Data
public class AirlaneNotFoundException extends RuntimeException {
    public AirlaneNotFoundException() {
        this("Aereolínea no econtontrada.");
    }
    public AirlaneNotFoundException(String message) {
        super(message);
    }
    public AirlaneNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
