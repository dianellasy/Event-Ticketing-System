package com.example.event_ticketing_system.exception;

// Custom exception thrown when a request is invalid or violates business rules (sold out, duplicate booking, etc.)
public class BadRequestException extends RuntimeException {
    // Passes an error message to the parent RuntimeException
    public BadRequestException(String message) {
        super(message);
    }
}
