package com.example.event_ticketing_system.dto;

import lombok.Data;

import java.util.List;

// DTO representing an attendee along with all their booking details
@Data
public class AttendeeBookingsDTO {
    // Unique identifier for the attendee
    private Integer attendeeId;

    // Full name of the attendee
    private String name;

    // List of detailed booking responses for this attendee
    private List<BookingResponseDTO> bookings;
}