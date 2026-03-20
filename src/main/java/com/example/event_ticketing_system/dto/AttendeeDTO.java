package com.example.event_ticketing_system.dto;

import lombok.Data;

// DTO representing basic attendee information returned to the client
@Data
public class AttendeeDTO {
    // Unique identifier for the attendee
    private Integer attendeeId;

    // Full name of the attendee
    private String name;

    // Email address used for identification and booking lookups
    private String email;
}