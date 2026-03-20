package com.example.event_ticketing_system.exception;

// Custom exception thrown when a requested resource cannot be found (an entity does not exist)
public class NotFoundException extends RuntimeException {
    // Passes an error message to the parent RuntimeException
    public NotFoundException(String message) {
        super(message);
    }
}